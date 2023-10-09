/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.cloud.bigtable.data.v2.stub;

import com.google.api.core.ApiFuture;
import com.google.api.core.BetaApi;
import com.google.api.core.InternalApi;
import com.google.api.gax.batching.Batcher;
import com.google.api.gax.batching.BatcherImpl;
import com.google.api.gax.batching.FlowController;
import com.google.api.gax.core.BackgroundResource;
import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.api.gax.grpc.GaxGrpcProperties;
import com.google.api.gax.grpc.GrpcCallContext;
import com.google.api.gax.grpc.GrpcCallSettings;
import com.google.api.gax.grpc.GrpcRawCallableFactory;
import com.google.api.gax.grpc.InstantiatingGrpcChannelProvider;
import com.google.api.gax.retrying.ExponentialRetryAlgorithm;
import com.google.api.gax.retrying.RetryAlgorithm;
import com.google.api.gax.retrying.RetryingExecutorWithContext;
import com.google.api.gax.retrying.ScheduledRetryingExecutor;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.Callables;
import com.google.api.gax.rpc.ClientContext;
import com.google.api.gax.rpc.RequestParamsExtractor;
import com.google.api.gax.rpc.ServerStreamingCallSettings;
import com.google.api.gax.rpc.ServerStreamingCallable;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.api.gax.tracing.OpencensusTracerFactory;
import com.google.api.gax.tracing.SpanName;
import com.google.api.gax.tracing.TracedServerStreamingCallable;
import com.google.api.gax.tracing.TracedUnaryCallable;
import com.google.auth.Credentials;
import com.google.auth.oauth2.ServiceAccountJwtAccessCredentials;
import com.google.bigtable.v2.BigtableGrpc;
import com.google.bigtable.v2.CheckAndMutateRowRequest;
import com.google.bigtable.v2.CheckAndMutateRowResponse;
import com.google.bigtable.v2.GenerateInitialChangeStreamPartitionsRequest;
import com.google.bigtable.v2.GenerateInitialChangeStreamPartitionsResponse;
import com.google.bigtable.v2.MutateRowRequest;
import com.google.bigtable.v2.MutateRowResponse;
import com.google.bigtable.v2.MutateRowsRequest;
import com.google.bigtable.v2.MutateRowsResponse;
import com.google.bigtable.v2.PingAndWarmRequest;
import com.google.bigtable.v2.PingAndWarmResponse;
import com.google.bigtable.v2.ReadChangeStreamRequest;
import com.google.bigtable.v2.ReadChangeStreamResponse;
import com.google.bigtable.v2.ReadModifyWriteRowRequest;
import com.google.bigtable.v2.ReadModifyWriteRowResponse;
import com.google.bigtable.v2.ReadRowsRequest;
import com.google.bigtable.v2.ReadRowsResponse;
import com.google.bigtable.v2.RowRange;
import com.google.bigtable.v2.SampleRowKeysRequest;
import com.google.bigtable.v2.SampleRowKeysResponse;
import com.google.cloud.bigtable.Version;
import com.google.cloud.bigtable.data.v2.internal.JwtCredentialsWithAudience;
import com.google.cloud.bigtable.data.v2.internal.RequestContext;
import com.google.cloud.bigtable.data.v2.models.BulkMutation;
import com.google.cloud.bigtable.data.v2.models.ChangeStreamMutation;
import com.google.cloud.bigtable.data.v2.models.ChangeStreamRecord;
import com.google.cloud.bigtable.data.v2.models.ChangeStreamRecordAdapter;
import com.google.cloud.bigtable.data.v2.models.ConditionalRowMutation;
import com.google.cloud.bigtable.data.v2.models.DefaultChangeStreamRecordAdapter;
import com.google.cloud.bigtable.data.v2.models.DefaultRowAdapter;
import com.google.cloud.bigtable.data.v2.models.KeyOffset;
import com.google.cloud.bigtable.data.v2.models.Query;
import com.google.cloud.bigtable.data.v2.models.Range.ByteStringRange;
import com.google.cloud.bigtable.data.v2.models.ReadChangeStreamQuery;
import com.google.cloud.bigtable.data.v2.models.ReadModifyWriteRow;
import com.google.cloud.bigtable.data.v2.models.Row;
import com.google.cloud.bigtable.data.v2.models.RowAdapter;
import com.google.cloud.bigtable.data.v2.models.RowMutation;
import com.google.cloud.bigtable.data.v2.models.RowMutationEntry;
import com.google.cloud.bigtable.data.v2.stub.changestream.ChangeStreamRecordMergingCallable;
import com.google.cloud.bigtable.data.v2.stub.changestream.GenerateInitialChangeStreamPartitionsUserCallable;
import com.google.cloud.bigtable.data.v2.stub.changestream.ReadChangeStreamResumptionStrategy;
import com.google.cloud.bigtable.data.v2.stub.changestream.ReadChangeStreamUserCallable;
import com.google.cloud.bigtable.data.v2.stub.metrics.BigtableTracerStreamingCallable;
import com.google.cloud.bigtable.data.v2.stub.metrics.BigtableTracerUnaryCallable;
import com.google.cloud.bigtable.data.v2.stub.metrics.BuiltinMetricsTracerFactory;
import com.google.cloud.bigtable.data.v2.stub.metrics.CompositeTracerFactory;
import com.google.cloud.bigtable.data.v2.stub.metrics.MetricsTracerFactory;
import com.google.cloud.bigtable.data.v2.stub.metrics.RpcMeasureConstants;
import com.google.cloud.bigtable.data.v2.stub.metrics.StatsHeadersServerStreamingCallable;
import com.google.cloud.bigtable.data.v2.stub.metrics.StatsHeadersUnaryCallable;
import com.google.cloud.bigtable.data.v2.stub.metrics.TracedBatcherUnaryCallable;
import com.google.cloud.bigtable.data.v2.stub.mutaterows.BulkMutateRowsUserFacingCallable;
import com.google.cloud.bigtable.data.v2.stub.mutaterows.MutateRowsBatchingDescriptor;
import com.google.cloud.bigtable.data.v2.stub.mutaterows.MutateRowsRetryingCallable;
import com.google.cloud.bigtable.data.v2.stub.readrows.FilterMarkerRowsCallable;
import com.google.cloud.bigtable.data.v2.stub.readrows.ReadRowsBatchingDescriptor;
import com.google.cloud.bigtable.data.v2.stub.readrows.ReadRowsFirstCallable;
import com.google.cloud.bigtable.data.v2.stub.readrows.ReadRowsResumptionStrategy;
import com.google.cloud.bigtable.data.v2.stub.readrows.ReadRowsRetryCompletedCallable;
import com.google.cloud.bigtable.data.v2.stub.readrows.ReadRowsUserCallable;
import com.google.cloud.bigtable.data.v2.stub.readrows.RowMergingCallable;
import com.google.cloud.bigtable.gaxx.retrying.ApiResultRetryAlgorithm;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.protobuf.ByteString;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.ForwardingClientCall;
import io.grpc.ForwardingClientCallListener;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.opencensus.stats.Stats;
import io.opencensus.stats.StatsRecorder;
import io.opencensus.tags.TagKey;
import io.opencensus.tags.TagValue;
import io.opencensus.tags.Tagger;
import io.opencensus.tags.Tags;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.threeten.bp.Duration;
import org.threeten.bp.Instant;

/**
 * The core client that converts method calls to RPCs.
 *
 * <p>This class consists of a set of Callable chains that represent RPC methods. There is a chain
 * for each RPC method. Each chain starts with a transformation that takes a protobuf wrapper and
 * terminates in a Callable that a gax gRPC callable. This class is meant to be a semantically
 * complete facade for the Bigtable data API. However it is not meant to be consumed directly,
 * please use {@link com.google.cloud.bigtable.data.v2.BigtableDataClient}.
 *
 * <p>This class is considered an internal implementation detail and not meant to be used by
 * applications.
 */
@InternalApi
public class EnhancedBigtableStub implements AutoCloseable {
  private static final String CLIENT_NAME = "Bigtable";
  private static final long FLOW_CONTROL_ADJUSTING_INTERVAL_MS = TimeUnit.SECONDS.toMillis(20);

  private final EnhancedBigtableStubSettings settings;
  private final ClientContext clientContext;
  private final RequestContext requestContext;
  private final FlowController bulkMutationFlowController;
  private final DynamicFlowControlStats bulkMutationDynamicFlowControlStats;

  private final ServerStreamingCallable<Query, Row> readRowsCallable;
  private final UnaryCallable<Query, Row> readRowCallable;
  private final UnaryCallable<Query, List<Row>> bulkReadRowsCallable;
  private final UnaryCallable<String, List<KeyOffset>> sampleRowKeysCallable;
  private final UnaryCallable<RowMutation, Void> mutateRowCallable;
  private final UnaryCallable<BulkMutation, Void> bulkMutateRowsCallable;
  private final UnaryCallable<ConditionalRowMutation, Boolean> checkAndMutateRowCallable;
  private final UnaryCallable<ReadModifyWriteRow, Row> readModifyWriteRowCallable;
  private final UnaryCallable<PingAndWarmRequest, PingAndWarmResponse> pingAndWarmCallable;

  private final ServerStreamingCallable<String, ByteStringRange>
      generateInitialChangeStreamPartitionsCallable;

  private final ServerStreamingCallable<ReadChangeStreamQuery, ChangeStreamRecord>
      readChangeStreamCallable;

  public static EnhancedBigtableStub create(EnhancedBigtableStubSettings settings)
      throws IOException {
    settings = finalizeSettings(settings, Tags.getTagger(), Stats.getStatsRecorder());

    return new EnhancedBigtableStub(settings, ClientContext.create(settings));
  }

  public static EnhancedBigtableStubSettings finalizeSettings(
      EnhancedBigtableStubSettings settings, Tagger tagger, StatsRecorder stats)
      throws IOException {
    EnhancedBigtableStubSettings.Builder builder = settings.toBuilder();

    // TODO: this implementation is on the cusp of unwieldy, if we end up adding more features
    // consider splitting it up by feature.

    // workaround JWT audience issues
    patchCredentials(builder);

    // Inject channel priming
    if (settings.isRefreshingChannel()) {
      // Fix the credentials so that they can be shared
      Credentials credentials = null;
      if (builder.getCredentialsProvider() != null) {
        credentials = builder.getCredentialsProvider().getCredentials();
      }
      builder.setCredentialsProvider(FixedCredentialsProvider.create(credentials));

      // Inject the primer
      InstantiatingGrpcChannelProvider transportProvider =
          (InstantiatingGrpcChannelProvider) settings.getTransportChannelProvider();

      builder.setTransportChannelProvider(
          transportProvider
              .toBuilder()
              .setChannelPrimer(
                  BigtableChannelPrimer.create(
                      credentials,
                      settings.getProjectId(),
                      settings.getInstanceId(),
                      settings.getAppProfileId()))
              .build());
    }

    ImmutableMap<TagKey, TagValue> attributes =
        ImmutableMap.<TagKey, TagValue>builder()
            .put(RpcMeasureConstants.BIGTABLE_PROJECT_ID, TagValue.create(settings.getProjectId()))
            .put(
                RpcMeasureConstants.BIGTABLE_INSTANCE_ID, TagValue.create(settings.getInstanceId()))
            .put(
                RpcMeasureConstants.BIGTABLE_APP_PROFILE_ID,
                TagValue.create(settings.getAppProfileId()))
            .build();
    ImmutableMap<String, String> builtinAttributes =
        ImmutableMap.<String, String>builder()
            .put("project_id", settings.getProjectId())
            .put("instance", settings.getInstanceId())
            .put("app_profile", settings.getAppProfileId())
            .build();
    // Inject Opencensus instrumentation
    builder.setTracerFactory(
        new CompositeTracerFactory(
            ImmutableList.of(
                // Add OpenCensus Tracing
                new OpencensusTracerFactory(
                    ImmutableMap.<String, String>builder()
                        // Annotate traces with the same tags as metrics
                        .put(
                            RpcMeasureConstants.BIGTABLE_PROJECT_ID.getName(),
                            settings.getProjectId())
                        .put(
                            RpcMeasureConstants.BIGTABLE_INSTANCE_ID.getName(),
                            settings.getInstanceId())
                        .put(
                            RpcMeasureConstants.BIGTABLE_APP_PROFILE_ID.getName(),
                            settings.getAppProfileId())
                        // Also annotate traces with library versions
                        .put("gax", GaxGrpcProperties.getGaxGrpcVersion())
                        .put("grpc", GaxGrpcProperties.getGrpcVersion())
                        .put("gapic", Version.VERSION)
                        .build()),
                // Add OpenCensus Metrics
                MetricsTracerFactory.create(tagger, stats, attributes),
                BuiltinMetricsTracerFactory.create(builtinAttributes),
                // Add user configured tracer
                settings.getTracerFactory())));

    if (builder.getTransportChannelProvider() instanceof InstantiatingGrpcChannelProvider) {
      // Inject RPC logging, ChannelPrimer use fixed channel so will be skipped here
      InstantiatingGrpcChannelProvider transportProvider =
          (InstantiatingGrpcChannelProvider) builder.getTransportChannelProvider();

      builder.setTransportChannelProvider(
          transportProvider
              .toBuilder()
              .setInterceptorProvider(
                  () -> {
                    OutstandingRpcLogger interceptor = new OutstandingRpcLogger();
                    interceptor.startLogging();
                    return ImmutableList.of(interceptor);
                  })
              .build());
    }

    return builder.build();
  }

  private static void patchCredentials(EnhancedBigtableStubSettings.Builder settings)
      throws IOException {
    int i = settings.getEndpoint().lastIndexOf(":");
    String host = settings.getEndpoint().substring(0, i);
    String audience = settings.getJwtAudienceMapping().get(host);

    if (audience == null) {
      return;
    }
    URI audienceUri = null;
    try {
      audienceUri = new URI(audience);
    } catch (URISyntaxException e) {
      throw new IllegalStateException("invalid JWT audience override", e);
    }

    CredentialsProvider credentialsProvider = settings.getCredentialsProvider();
    if (credentialsProvider == null) {
      return;
    }

    Credentials credentials = credentialsProvider.getCredentials();
    if (credentials == null) {
      return;
    }

    if (!(credentials instanceof ServiceAccountJwtAccessCredentials)) {
      return;
    }

    ServiceAccountJwtAccessCredentials jwtCreds = (ServiceAccountJwtAccessCredentials) credentials;
    JwtCredentialsWithAudience patchedCreds = new JwtCredentialsWithAudience(jwtCreds, audienceUri);
    settings.setCredentialsProvider(FixedCredentialsProvider.create(patchedCreds));
  }

  public EnhancedBigtableStub(EnhancedBigtableStubSettings settings, ClientContext clientContext) {
    this.settings = settings;
    this.clientContext = clientContext;
    this.requestContext =
        RequestContext.create(
            settings.getProjectId(), settings.getInstanceId(), settings.getAppProfileId());
    this.bulkMutationFlowController =
        new FlowController(settings.bulkMutateRowsSettings().getDynamicFlowControlSettings());
    this.bulkMutationDynamicFlowControlStats = new DynamicFlowControlStats();

    readRowsCallable = createReadRowsCallable(new DefaultRowAdapter());
    readRowCallable = createReadRowCallable(new DefaultRowAdapter());
    bulkReadRowsCallable = createBulkReadRowsCallable(new DefaultRowAdapter());
    sampleRowKeysCallable = createSampleRowKeysCallable();
    mutateRowCallable = createMutateRowCallable();
    bulkMutateRowsCallable = createBulkMutateRowsCallable();
    checkAndMutateRowCallable = createCheckAndMutateRowCallable();
    readModifyWriteRowCallable = createReadModifyWriteRowCallable();
    generateInitialChangeStreamPartitionsCallable =
        createGenerateInitialChangeStreamPartitionsCallable();
    readChangeStreamCallable =
        createReadChangeStreamCallable(new DefaultChangeStreamRecordAdapter());
    pingAndWarmCallable = createPingAndWarmCallable();
  }

  // <editor-fold desc="Callable creators">

  /**
   * Creates a callable chain to handle ReadRows RPCs. The chain will:
   *
   * <ul>
   *   <li>Dispatch the RPC with {@link ReadRowsRequest}.
   *   <li>Upon receiving the response stream, it will merge the {@link
   *       com.google.bigtable.v2.ReadRowsResponse.CellChunk}s in logical rows. The actual row
   *       implementation can be configured by the {@code rowAdapter} parameter.
   *   <li>Retry/resume on failure.
   *   <li>Filter out marker rows.
   * </ul>
   *
   * <p>NOTE: the caller is responsible for adding tracing & metrics.
   */
  @BetaApi("This surface is stable yet it might be removed in the future.")
  public <RowT> ServerStreamingCallable<ReadRowsRequest, RowT> createReadRowsRawCallable(
      RowAdapter<RowT> rowAdapter) {
    return createReadRowsBaseCallable(settings.readRowsSettings(), rowAdapter)
        .withDefaultCallContext(clientContext.getDefaultCallContext());
  }

  /**
   * Creates a callable chain to handle streaming ReadRows RPCs. The chain will:
   *
   * <ul>
   *   <li>Convert a {@link Query} into a {@link com.google.bigtable.v2.ReadRowsRequest} and
   *       dispatch the RPC.
   *   <li>Upon receiving the response stream, it will merge the {@link
   *       com.google.bigtable.v2.ReadRowsResponse.CellChunk}s in logical rows. The actual row
   *       implementation can be configured in by the {@code rowAdapter} parameter.
   *   <li>Retry/resume on failure.
   *   <li>Filter out marker rows.
   *   <li>Add tracing & metrics.
   * </ul>
   */
  public <RowT> ServerStreamingCallable<Query, RowT> createReadRowsCallable(
      RowAdapter<RowT> rowAdapter) {
    ServerStreamingCallable<ReadRowsRequest, RowT> readRowsCallable =
        createReadRowsBaseCallable(settings.readRowsSettings(), rowAdapter);

    ServerStreamingCallable<Query, RowT> readRowsUserCallable =
        new ReadRowsUserCallable<>(readRowsCallable, requestContext);

    SpanName span = getSpanName("ReadRows");
    ServerStreamingCallable<Query, RowT> traced =
        new TracedServerStreamingCallable<>(
            readRowsUserCallable, clientContext.getTracerFactory(), span);

    return traced.withDefaultCallContext(clientContext.getDefaultCallContext());
  }

  /**
   * Creates a callable chain to handle point ReadRows RPCs. The chain will:
   *
   * <ul>
   *   <li>Convert a {@link Query} into a {@link com.google.bigtable.v2.ReadRowsRequest} and
   *       dispatch the RPC.
   *   <li>Upon receiving the response stream, it will merge the {@link
   *       com.google.bigtable.v2.ReadRowsResponse.CellChunk}s in logical rows. The actual row
   *       implementation can be configured in by the {@code rowAdapter} parameter.
   *   <li>Retry/resume on failure.
   *   <li>Filter out marker rows.
   *   <li>Add tracing & metrics.
   * </ul>
   */
  public <RowT> UnaryCallable<Query, RowT> createReadRowCallable(RowAdapter<RowT> rowAdapter) {
    ServerStreamingCallable<ReadRowsRequest, RowT> readRowsCallable =
        createReadRowsBaseCallable(
            ServerStreamingCallSettings.<ReadRowsRequest, Row>newBuilder()
                .setRetryableCodes(settings.readRowSettings().getRetryableCodes())
                .setRetrySettings(settings.readRowSettings().getRetrySettings())
                .setIdleTimeout(settings.readRowSettings().getRetrySettings().getTotalTimeout())
                .build(),
            rowAdapter);

    ReadRowsUserCallable<RowT> readRowCallable =
        new ReadRowsUserCallable<>(readRowsCallable, requestContext);

    ReadRowsFirstCallable<RowT> firstRow = new ReadRowsFirstCallable<>(readRowCallable);

    UnaryCallable<Query, RowT> traced =
        new TracedUnaryCallable<>(
            firstRow, clientContext.getTracerFactory(), getSpanName("ReadRow"));

    return traced.withDefaultCallContext(clientContext.getDefaultCallContext());
  }

  /**
   * Creates a callable chain to handle ReadRows RPCs. The chain will:
   *
   * <ul>
   *   <li>Dispatch the RPC with {@link ReadRowsRequest}.
   *   <li>Upon receiving the response stream, it will merge the {@link
   *       com.google.bigtable.v2.ReadRowsResponse.CellChunk}s in logical rows. The actual row
   *       implementation can be configured by the {@code rowAdapter} parameter.
   *   <li>Add bigtable tracer for tracking bigtable specific metrics.
   *   <li>Retry/resume on failure.
   *   <li>Filter out marker rows.
   * </ul>
   *
   * <p>NOTE: the caller is responsible for adding tracing & metrics.
   */
  private <ReqT, RowT> ServerStreamingCallable<ReadRowsRequest, RowT> createReadRowsBaseCallable(
      ServerStreamingCallSettings<ReqT, Row> readRowsSettings, RowAdapter<RowT> rowAdapter) {

    ServerStreamingCallable<ReadRowsRequest, ReadRowsResponse> base =
        GrpcRawCallableFactory.createServerStreamingCallable(
            GrpcCallSettings.<ReadRowsRequest, ReadRowsResponse>newBuilder()
                .setMethodDescriptor(BigtableGrpc.getReadRowsMethod())
                .setParamsExtractor(
                    new RequestParamsExtractor<ReadRowsRequest>() {
                      @Override
                      public Map<String, String> extract(ReadRowsRequest readRowsRequest) {
                        return ImmutableMap.of(
                            "table_name", readRowsRequest.getTableName(),
                            "app_profile_id", readRowsRequest.getAppProfileId());
                      }
                    })
                .build(),
            readRowsSettings.getRetryableCodes());

    ServerStreamingCallable<ReadRowsRequest, ReadRowsResponse> withStatsHeaders =
        new StatsHeadersServerStreamingCallable<>(base);

    // Sometimes ReadRows connections are disconnected via an RST frame. This error is transient and
    // should be treated similar to UNAVAILABLE. However, this exception has an INTERNAL error code
    // which by default is not retryable. Convert the exception so it can be retried in the client.
    ServerStreamingCallable<ReadRowsRequest, ReadRowsResponse> convertException =
        new ConvertExceptionCallable<>(withStatsHeaders);

    ServerStreamingCallable<ReadRowsRequest, RowT> merging =
        new RowMergingCallable<>(convertException, rowAdapter);

    // Copy settings for the middle ReadRowsRequest -> RowT callable (as opposed to the inner
    // ReadRowsRequest -> ReadRowsResponse callable).
    ServerStreamingCallSettings<ReadRowsRequest, RowT> innerSettings =
        ServerStreamingCallSettings.<ReadRowsRequest, RowT>newBuilder()
            .setResumptionStrategy(new ReadRowsResumptionStrategy<>(rowAdapter))
            .setRetryableCodes(readRowsSettings.getRetryableCodes())
            .setRetrySettings(readRowsSettings.getRetrySettings())
            .setIdleTimeout(readRowsSettings.getIdleTimeout())
            .setWaitTimeout(readRowsSettings.getWaitTimeout())
            .build();

    ServerStreamingCallable<ReadRowsRequest, RowT> watched =
        Callables.watched(merging, innerSettings, clientContext);

    ServerStreamingCallable<ReadRowsRequest, RowT> withBigtableTracer =
        new BigtableTracerStreamingCallable<>(watched);

    // Retry logic is split into 2 parts to workaround a rare edge case described in
    // ReadRowsRetryCompletedCallable
    ServerStreamingCallable<ReadRowsRequest, RowT> retrying1 =
        new ReadRowsRetryCompletedCallable<>(withBigtableTracer);

    ServerStreamingCallable<ReadRowsRequest, RowT> retrying2 =
        Callables.retrying(retrying1, innerSettings, clientContext);

    return new FilterMarkerRowsCallable<>(retrying2, rowAdapter);
  }

  /**
   * Creates a callable chain to handle bulk ReadRows RPCs. This is meant to be used in ReadRows
   * batcher. The chain will:
   *
   * <ul>
   *   <li>Convert a {@link Query} into a {@link com.google.bigtable.v2.ReadRowsRequest}.
   *   <li>Upon receiving the response stream, it will merge the {@link
   *       com.google.bigtable.v2.ReadRowsResponse.CellChunk}s in logical rows. The actual row
   *       implementation can be configured in by the {@code rowAdapter} parameter.
   *   <li>Retry/resume on failure.
   *   <li>Filter out marker rows.
   *   <li>Construct a {@link UnaryCallable} that will buffer the entire stream into memory before
   *       completing. If the stream is empty, then the list will be empty.
   *   <li>Add tracing & metrics.
   * </ul>
   */
  private <RowT> UnaryCallable<Query, List<RowT>> createBulkReadRowsCallable(
      RowAdapter<RowT> rowAdapter) {
    ServerStreamingCallable<ReadRowsRequest, RowT> readRowsCallable =
        createReadRowsBaseCallable(settings.readRowsSettings(), rowAdapter);

    ServerStreamingCallable<Query, RowT> readRowsUserCallable =
        new ReadRowsUserCallable<>(readRowsCallable, requestContext);

    SpanName span = getSpanName("ReadRows");

    // The TracedBatcherUnaryCallable has to be wrapped by the TracedUnaryCallable, so that
    // TracedUnaryCallable can inject a tracer for the TracedBatcherUnaryCallable to interact with
    UnaryCallable<Query, List<RowT>> tracedBatcher =
        new TracedBatcherUnaryCallable<>(readRowsUserCallable.all());

    UnaryCallable<Query, List<RowT>> traced =
        new TracedUnaryCallable<>(tracedBatcher, clientContext.getTracerFactory(), span);

    return traced.withDefaultCallContext(clientContext.getDefaultCallContext());
  }

  /**
   * Creates a callable chain to handle SampleRowKeys RPcs. The chain will:
   *
   * <ul>
   *   <li>Convert a table id to a {@link com.google.bigtable.v2.SampleRowKeysRequest}.
   *   <li>Dispatch the request to the GAPIC's {@link BigtableStub#sampleRowKeysCallable()}.
   *   <li>Spool responses into a list.
   *   <li>Retry on failure.
   *   <li>Convert the responses into {@link KeyOffset}s.
   *   <li>Add tracing & metrics.
   * </ul>
   */
  private UnaryCallable<String, List<KeyOffset>> createSampleRowKeysCallable() {
    String methodName = "SampleRowKeys";

    ServerStreamingCallable<SampleRowKeysRequest, SampleRowKeysResponse> base =
        GrpcRawCallableFactory.createServerStreamingCallable(
            GrpcCallSettings.<SampleRowKeysRequest, SampleRowKeysResponse>newBuilder()
                .setMethodDescriptor(BigtableGrpc.getSampleRowKeysMethod())
                .setParamsExtractor(
                    new RequestParamsExtractor<SampleRowKeysRequest>() {
                      @Override
                      public Map<String, String> extract(
                          SampleRowKeysRequest sampleRowKeysRequest) {
                        return ImmutableMap.of(
                            "table_name", sampleRowKeysRequest.getTableName(),
                            "app_profile_id", sampleRowKeysRequest.getAppProfileId());
                      }
                    })
                .build(),
            settings.sampleRowKeysSettings().getRetryableCodes());

    UnaryCallable<SampleRowKeysRequest, List<SampleRowKeysResponse>> spoolable = base.all();

    UnaryCallable<SampleRowKeysRequest, List<SampleRowKeysResponse>> withStatsHeaders =
        new StatsHeadersUnaryCallable<>(spoolable);

    UnaryCallable<SampleRowKeysRequest, List<SampleRowKeysResponse>> withBigtableTracer =
        new BigtableTracerUnaryCallable<>(withStatsHeaders);

    UnaryCallable<SampleRowKeysRequest, List<SampleRowKeysResponse>> retryable =
        Callables.retrying(withBigtableTracer, settings.sampleRowKeysSettings(), clientContext);

    return createUserFacingUnaryCallable(
        methodName, new SampleRowKeysCallable(retryable, requestContext));
  }

  /**
   * Creates a callable chain to handle MutateRow RPCs. The chain will:
   *
   * <ul>
   *   <li>Convert a {@link RowMutation} into a {@link com.google.bigtable.v2.MutateRowRequest}.
   *   <li>Add tracing & metrics.
   * </ul>
   */
  private UnaryCallable<RowMutation, Void> createMutateRowCallable() {
    String methodName = "MutateRow";
    UnaryCallable<MutateRowRequest, MutateRowResponse> base =
        GrpcRawCallableFactory.createUnaryCallable(
            GrpcCallSettings.<MutateRowRequest, MutateRowResponse>newBuilder()
                .setMethodDescriptor(BigtableGrpc.getMutateRowMethod())
                .setParamsExtractor(
                    new RequestParamsExtractor<MutateRowRequest>() {
                      @Override
                      public Map<String, String> extract(MutateRowRequest mutateRowRequest) {
                        return ImmutableMap.of(
                            "table_name", mutateRowRequest.getTableName(),
                            "app_profile_id", mutateRowRequest.getAppProfileId());
                      }
                    })
                .build(),
            settings.mutateRowSettings().getRetryableCodes());

    UnaryCallable<MutateRowRequest, MutateRowResponse> withStatsHeaders =
        new StatsHeadersUnaryCallable<>(base);

    UnaryCallable<MutateRowRequest, MutateRowResponse> withBigtableTracer =
        new BigtableTracerUnaryCallable<>(withStatsHeaders);

    UnaryCallable<MutateRowRequest, MutateRowResponse> retrying =
        Callables.retrying(withBigtableTracer, settings.mutateRowSettings(), clientContext);

    return createUserFacingUnaryCallable(
        methodName, new MutateRowCallable(retrying, requestContext));
  }

  /**
   * Creates a callable chain to handle MutatesRows RPCs. This is meant to be used for manual
   * batching. The chain will:
   *
   * <ul>
   *   <li>Convert a {@link BulkMutation} into a {@link MutateRowsRequest}.
   *   <li>Process the response and schedule retries. At the end of each attempt, entries that have
   *       been applied, are filtered from the next attempt. Also, any entries that failed with a
   *       nontransient error, are filtered from the next attempt. This will continue until there
   *       are no more entries or there are no more retry attempts left.
   *   <li>Wrap batch failures in a {@link
   *       com.google.cloud.bigtable.data.v2.models.MutateRowsException}.
   *   <li>Add tracing & metrics.
   * </ul>
   */
  private UnaryCallable<BulkMutation, Void> createBulkMutateRowsCallable() {
    LoggerChain loggerChain = new LoggerChain();

    UnaryCallable<MutateRowsRequest, Void> baseCallable = createMutateRowsBaseCallable();

    loggerChain.instrument("base", baseCallable);

    UnaryCallable<MutateRowsRequest, Void> flowControlCallable = null;
    if (settings.bulkMutateRowsSettings().isLatencyBasedThrottlingEnabled()) {
      flowControlCallable =
          new DynamicFlowControlCallable(
              baseCallable,
              bulkMutationFlowController,
              bulkMutationDynamicFlowControlStats,
              settings.bulkMutateRowsSettings().getTargetRpcLatencyMs(),
              FLOW_CONTROL_ADJUSTING_INTERVAL_MS);
    }
    UnaryCallable<BulkMutation, Void> userFacing =
        new BulkMutateRowsUserFacingCallable(
            flowControlCallable != null ? flowControlCallable : baseCallable, requestContext);

    loggerChain.instrument("userFacing", userFacing);

    SpanName spanName = getSpanName("MutateRows");

    UnaryCallable<BulkMutation, Void> tracedBatcherUnaryCallable =
        new TracedBatcherUnaryCallable<>(userFacing);
    loggerChain.instrument("tracedBatcherUnary", tracedBatcherUnaryCallable);

    UnaryCallable<BulkMutation, Void> traced =
        new TracedUnaryCallable<>(
            tracedBatcherUnaryCallable, clientContext.getTracerFactory(), spanName);
    loggerChain.instrument("traced", traced);

    loggerChain.startLogging();

    return traced.withDefaultCallContext(clientContext.getDefaultCallContext());
  }

  /**
   * Creates a {@link BatcherImpl} to handle {@link MutateRowsRequest.Entry} mutations. This is
   * meant to be used for automatic batching with flow control.
   *
   * <ul>
   *   <li>Uses {@link MutateRowsBatchingDescriptor} to spool the {@link RowMutationEntry} mutations
   *       and send them out as {@link BulkMutation}.
   *   <li>Uses {@link #bulkMutateRowsCallable()} to perform RPC.
   *   <li>Batching thresholds can be configured from {@link
   *       EnhancedBigtableStubSettings#bulkMutateRowsSettings()}.
   *   <li>Process the response and schedule retries. At the end of each attempt, entries that have
   *       been applied, are filtered from the next attempt. Also, any entries that failed with a
   *       nontransient error, are filtered from the next attempt. This will continue until there
   *       are no more entries or there are no more retry attempts left.
   *   <li>Wrap batch failures in a {@link
   *       com.google.cloud.bigtable.data.v2.models.MutateRowsException}.
   *   <li>Split the responses using {@link MutateRowsBatchingDescriptor}.
   * </ul>
   */
  public Batcher<RowMutationEntry, Void> newMutateRowsBatcher(
      @Nonnull String tableId, @Nullable GrpcCallContext ctx) {
    return new BatcherImpl<>(
        settings.bulkMutateRowsSettings().getBatchingDescriptor(),
        bulkMutateRowsCallable,
        BulkMutation.create(tableId),
        settings.bulkMutateRowsSettings().getBatchingSettings(),
        clientContext.getExecutor(),
        bulkMutationFlowController,
        MoreObjects.firstNonNull(ctx, clientContext.getDefaultCallContext()));
  }

  /**
   * Creates a {@link BatcherImpl} to handle {@link Query#rowKey(String)}. This is meant for bulk
   * read with flow control.
   *
   * <ul>
   *   <li>Uses {@link ReadRowsBatchingDescriptor} to merge the row-keys and send them out as {@link
   *       Query}.
   *   <li>Uses {@link #readRowsCallable()} to perform RPC.
   *   <li>Batching thresholds can be configured from {@link
   *       EnhancedBigtableStubSettings#bulkReadRowsSettings()}.
   *   <li>Schedule retries for retryable exceptions until there are no more entries or there are no
   *       more retry attempts left.
   *   <li>Split the responses using {@link ReadRowsBatchingDescriptor}.
   * </ul>
   */
  public Batcher<ByteString, Row> newBulkReadRowsBatcher(
      @Nonnull Query query, @Nullable GrpcCallContext ctx) {
    Preconditions.checkNotNull(query, "query cannot be null");
    return new BatcherImpl<>(
        settings.bulkReadRowsSettings().getBatchingDescriptor(),
        bulkReadRowsCallable,
        query,
        settings.bulkReadRowsSettings().getBatchingSettings(),
        clientContext.getExecutor(),
        null,
        MoreObjects.firstNonNull(ctx, clientContext.getDefaultCallContext()));
  }

  /**
   * Internal helper to create the base MutateRows callable chain. The chain is responsible for
   * retrying individual entry in case of error.
   *
   * <p>NOTE: the caller is responsible for adding tracing & metrics.
   *
   * @see MutateRowsRetryingCallable for more details
   */
  private UnaryCallable<MutateRowsRequest, Void> createMutateRowsBaseCallable() {
    ServerStreamingCallable<MutateRowsRequest, MutateRowsResponse> base =
        GrpcRawCallableFactory.createServerStreamingCallable(
            GrpcCallSettings.<MutateRowsRequest, MutateRowsResponse>newBuilder()
                .setMethodDescriptor(BigtableGrpc.getMutateRowsMethod())
                .setParamsExtractor(
                    new RequestParamsExtractor<MutateRowsRequest>() {
                      @Override
                      public Map<String, String> extract(MutateRowsRequest mutateRowsRequest) {
                        return ImmutableMap.of(
                            "table_name", mutateRowsRequest.getTableName(),
                            "app_profile_id", mutateRowsRequest.getAppProfileId());
                      }
                    })
                .build(),
            settings.bulkMutateRowsSettings().getRetryableCodes());

    ServerStreamingCallable<MutateRowsRequest, MutateRowsResponse> callable =
        new StatsHeadersServerStreamingCallable<>(base);

    if (settings.bulkMutateRowsSettings().isServerInitiatedFlowControlEnabled()) {
      callable = new RateLimitingServerStreamingCallable(callable);
    }

    // Sometimes MutateRows connections are disconnected via an RST frame. This error is transient
    // and
    // should be treated similar to UNAVAILABLE. However, this exception has an INTERNAL error code
    // which by default is not retryable. Convert the exception so it can be retried in the client.
    ServerStreamingCallable<MutateRowsRequest, MutateRowsResponse> convertException =
        new ConvertExceptionCallable<>(callable);

    ServerStreamingCallable<MutateRowsRequest, MutateRowsResponse> withBigtableTracer =
        new BigtableTracerStreamingCallable<>(convertException);

    // Set up watchdog to kill hanging streams
    ServerStreamingCallSettings<MutateRowsRequest, MutateRowsResponse> innerSettings =
        ServerStreamingCallSettings.<MutateRowsRequest, MutateRowsResponse>newBuilder()
            .setRetryableCodes(settings.bulkMutateRowsSettings().getRetryableCodes())
            .setRetrySettings(settings.bulkMutateRowsSettings().getRetrySettings())
            .setIdleTimeout(Duration.ofMinutes(5))
            .setWaitTimeout(Duration.ofMinutes(5))
            .build();

    ServerStreamingCallable<MutateRowsRequest, MutateRowsResponse> watched =
        Callables.watched(withBigtableTracer, innerSettings, clientContext);

    RetryAlgorithm<Void> retryAlgorithm =
        new RetryAlgorithm<>(
            new ApiResultRetryAlgorithm<Void>(),
            new ExponentialRetryAlgorithm(
                settings.bulkMutateRowsSettings().getRetrySettings(), clientContext.getClock()));
    RetryingExecutorWithContext<Void> retryingExecutor =
        new ScheduledRetryingExecutor<>(retryAlgorithm, clientContext.getExecutor());

    return new MutateRowsRetryingCallable(
        clientContext.getDefaultCallContext(),
        withBigtableTracer,
        retryingExecutor,
        settings.bulkMutateRowsSettings().getRetryableCodes());
  }

  /**
   * Creates a callable chain to handle CheckAndMutateRow RPCs. THe chain will:
   *
   * <ul>
   *   <li>Convert {@link ConditionalRowMutation}s into {@link
   *       com.google.bigtable.v2.CheckAndMutateRowRequest}s.
   *   <li>Add tracing & metrics.
   * </ul>
   */
  private UnaryCallable<ConditionalRowMutation, Boolean> createCheckAndMutateRowCallable() {
    String methodName = "CheckAndMutateRow";
    UnaryCallable<CheckAndMutateRowRequest, CheckAndMutateRowResponse> base =
        GrpcRawCallableFactory.createUnaryCallable(
            GrpcCallSettings.<CheckAndMutateRowRequest, CheckAndMutateRowResponse>newBuilder()
                .setMethodDescriptor(BigtableGrpc.getCheckAndMutateRowMethod())
                .setParamsExtractor(
                    new RequestParamsExtractor<CheckAndMutateRowRequest>() {
                      @Override
                      public Map<String, String> extract(
                          CheckAndMutateRowRequest checkAndMutateRowRequest) {
                        return ImmutableMap.of(
                            "table_name", checkAndMutateRowRequest.getTableName(),
                            "app_profile_id", checkAndMutateRowRequest.getAppProfileId());
                      }
                    })
                .build(),
            settings.checkAndMutateRowSettings().getRetryableCodes());

    UnaryCallable<CheckAndMutateRowRequest, CheckAndMutateRowResponse> withStatsHeaders =
        new StatsHeadersUnaryCallable<>(base);

    UnaryCallable<CheckAndMutateRowRequest, CheckAndMutateRowResponse> withBigtableTracer =
        new BigtableTracerUnaryCallable<>(withStatsHeaders);

    UnaryCallable<CheckAndMutateRowRequest, CheckAndMutateRowResponse> retrying =
        Callables.retrying(withBigtableTracer, settings.checkAndMutateRowSettings(), clientContext);

    return createUserFacingUnaryCallable(
        methodName, new CheckAndMutateRowCallable(retrying, requestContext));
  }

  /**
   * Creates a callable chain to handle ReadModifyWriteRow RPCs. The chain will:
   *
   * <ul>
   *   <li>Convert {@link ReadModifyWriteRow}s into {@link
   *       com.google.bigtable.v2.ReadModifyWriteRowRequest}s.
   *   <li>Convert the responses into {@link Row}.
   *   <li>Add tracing & metrics.
   * </ul>
   */
  private UnaryCallable<ReadModifyWriteRow, Row> createReadModifyWriteRowCallable() {
    UnaryCallable<ReadModifyWriteRowRequest, ReadModifyWriteRowResponse> base =
        GrpcRawCallableFactory.createUnaryCallable(
            GrpcCallSettings.<ReadModifyWriteRowRequest, ReadModifyWriteRowResponse>newBuilder()
                .setMethodDescriptor(BigtableGrpc.getReadModifyWriteRowMethod())
                .setParamsExtractor(
                    new RequestParamsExtractor<ReadModifyWriteRowRequest>() {
                      @Override
                      public Map<String, String> extract(ReadModifyWriteRowRequest request) {
                        return ImmutableMap.of(
                            "table_name", request.getTableName(),
                            "app_profile_id", request.getAppProfileId());
                      }
                    })
                .build(),
            settings.readModifyWriteRowSettings().getRetryableCodes());

    UnaryCallable<ReadModifyWriteRowRequest, ReadModifyWriteRowResponse> withStatsHeaders =
        new StatsHeadersUnaryCallable<>(base);

    String methodName = "ReadModifyWriteRow";
    UnaryCallable<ReadModifyWriteRowRequest, ReadModifyWriteRowResponse> withBigtableTracer =
        new BigtableTracerUnaryCallable<>(withStatsHeaders);

    UnaryCallable<ReadModifyWriteRowRequest, ReadModifyWriteRowResponse> retrying =
        Callables.retrying(
            withBigtableTracer, settings.readModifyWriteRowSettings(), clientContext);

    return createUserFacingUnaryCallable(
        methodName, new ReadModifyWriteRowCallable(retrying, requestContext));
  }

  /**
   * Creates a callable chain to handle streaming GenerateInitialChangeStreamPartitions RPCs. The
   * chain will:
   *
   * <ul>
   *   <li>Convert a String format tableId into a {@link
   *       GenerateInitialChangeStreamPartitionsRequest} and dispatch the RPC.
   *   <li>Upon receiving the response stream, it will convert the {@link
   *       com.google.bigtable.v2.GenerateInitialChangeStreamPartitionsResponse}s into {@link
   *       RowRange}.
   * </ul>
   */
  private ServerStreamingCallable<String, ByteStringRange>
      createGenerateInitialChangeStreamPartitionsCallable() {
    ServerStreamingCallable<
            GenerateInitialChangeStreamPartitionsRequest,
            GenerateInitialChangeStreamPartitionsResponse>
        base =
            GrpcRawCallableFactory.createServerStreamingCallable(
                GrpcCallSettings
                    .<GenerateInitialChangeStreamPartitionsRequest,
                        GenerateInitialChangeStreamPartitionsResponse>
                        newBuilder()
                    .setMethodDescriptor(
                        BigtableGrpc.getGenerateInitialChangeStreamPartitionsMethod())
                    .setParamsExtractor(
                        new RequestParamsExtractor<GenerateInitialChangeStreamPartitionsRequest>() {
                          @Override
                          public Map<String, String> extract(
                              GenerateInitialChangeStreamPartitionsRequest
                                  generateInitialChangeStreamPartitionsRequest) {
                            return ImmutableMap.of(
                                "table_name",
                                generateInitialChangeStreamPartitionsRequest.getTableName(),
                                "app_profile_id",
                                generateInitialChangeStreamPartitionsRequest.getAppProfileId());
                          }
                        })
                    .build(),
                settings.generateInitialChangeStreamPartitionsSettings().getRetryableCodes());

    ServerStreamingCallable<String, ByteStringRange> userCallable =
        new GenerateInitialChangeStreamPartitionsUserCallable(base, requestContext);

    ServerStreamingCallable<String, ByteStringRange> withStatsHeaders =
        new StatsHeadersServerStreamingCallable<>(userCallable);

    // Sometimes GenerateInitialChangeStreamPartitions connections are disconnected via an RST
    // frame. This error is transient and should be treated similar to UNAVAILABLE. However, this
    // exception has an INTERNAL error code which by default is not retryable. Convert the exception
    // so it can be retried in the client.
    ServerStreamingCallable<String, ByteStringRange> convertException =
        new ConvertExceptionCallable<>(withStatsHeaders);

    // Copy idle timeout settings for watchdog.
    ServerStreamingCallSettings<String, ByteStringRange> innerSettings =
        ServerStreamingCallSettings.<String, ByteStringRange>newBuilder()
            .setRetryableCodes(
                settings.generateInitialChangeStreamPartitionsSettings().getRetryableCodes())
            .setRetrySettings(
                settings.generateInitialChangeStreamPartitionsSettings().getRetrySettings())
            .setIdleTimeout(
                settings.generateInitialChangeStreamPartitionsSettings().getIdleTimeout())
            .setWaitTimeout(
                settings.generateInitialChangeStreamPartitionsSettings().getWaitTimeout())
            .build();

    ServerStreamingCallable<String, ByteStringRange> watched =
        Callables.watched(convertException, innerSettings, clientContext);

    ServerStreamingCallable<String, ByteStringRange> withBigtableTracer =
        new BigtableTracerStreamingCallable<>(watched);

    ServerStreamingCallable<String, ByteStringRange> retrying =
        Callables.retrying(withBigtableTracer, innerSettings, clientContext);

    SpanName span = getSpanName("GenerateInitialChangeStreamPartitions");
    ServerStreamingCallable<String, ByteStringRange> traced =
        new TracedServerStreamingCallable<>(retrying, clientContext.getTracerFactory(), span);

    return traced.withDefaultCallContext(clientContext.getDefaultCallContext());
  }

  /**
   * Creates a callable chain to handle streaming ReadChangeStream RPCs. The chain will:
   *
   * <ul>
   *   <li>Convert a {@link ReadChangeStreamQuery} into a {@link ReadChangeStreamRequest} and
   *       dispatch the RPC.
   *   <li>Upon receiving the response stream, it will produce a stream of ChangeStreamRecordT. In
   *       case of mutations, it will merge the {@link ReadChangeStreamResponse.DataChange}s into
   *       {@link ChangeStreamMutation}. The actual change stream record implementation can be
   *       configured by the {@code changeStreamRecordAdapter} parameter.
   *   <li>Retry/resume on failure.
   *   <li>Add tracing & metrics.
   * </ul>
   */
  public <ChangeStreamRecordT>
      ServerStreamingCallable<ReadChangeStreamQuery, ChangeStreamRecordT>
          createReadChangeStreamCallable(
              ChangeStreamRecordAdapter<ChangeStreamRecordT> changeStreamRecordAdapter) {
    ServerStreamingCallable<ReadChangeStreamRequest, ReadChangeStreamResponse> base =
        GrpcRawCallableFactory.createServerStreamingCallable(
            GrpcCallSettings.<ReadChangeStreamRequest, ReadChangeStreamResponse>newBuilder()
                .setMethodDescriptor(BigtableGrpc.getReadChangeStreamMethod())
                .setParamsExtractor(
                    new RequestParamsExtractor<ReadChangeStreamRequest>() {
                      @Override
                      public Map<String, String> extract(
                          ReadChangeStreamRequest readChangeStreamRequest) {
                        return ImmutableMap.of(
                            "table_name", readChangeStreamRequest.getTableName(),
                            "app_profile_id", readChangeStreamRequest.getAppProfileId());
                      }
                    })
                .build(),
            settings.readChangeStreamSettings().getRetryableCodes());

    ServerStreamingCallable<ReadChangeStreamRequest, ReadChangeStreamResponse> withStatsHeaders =
        new StatsHeadersServerStreamingCallable<>(base);

    // Sometimes ReadChangeStream connections are disconnected via an RST frame. This error is
    // transient and should be treated similar to UNAVAILABLE. However, this exception has an
    // INTERNAL error code which by default is not retryable. Convert the exception it can be
    // retried in the client.
    ServerStreamingCallable<ReadChangeStreamRequest, ReadChangeStreamResponse> convertException =
        new ConvertExceptionCallable<>(withStatsHeaders);

    ServerStreamingCallable<ReadChangeStreamRequest, ChangeStreamRecordT> merging =
        new ChangeStreamRecordMergingCallable<>(convertException, changeStreamRecordAdapter);

    // Copy idle timeout settings for watchdog.
    ServerStreamingCallSettings<ReadChangeStreamRequest, ChangeStreamRecordT> innerSettings =
        ServerStreamingCallSettings.<ReadChangeStreamRequest, ChangeStreamRecordT>newBuilder()
            .setResumptionStrategy(
                new ReadChangeStreamResumptionStrategy<>(changeStreamRecordAdapter))
            .setRetryableCodes(settings.readChangeStreamSettings().getRetryableCodes())
            .setRetrySettings(settings.readChangeStreamSettings().getRetrySettings())
            .setIdleTimeout(settings.readChangeStreamSettings().getIdleTimeout())
            .setWaitTimeout(settings.readChangeStreamSettings().getWaitTimeout())
            .build();

    ServerStreamingCallable<ReadChangeStreamRequest, ChangeStreamRecordT> watched =
        Callables.watched(merging, innerSettings, clientContext);

    ServerStreamingCallable<ReadChangeStreamRequest, ChangeStreamRecordT> withBigtableTracer =
        new BigtableTracerStreamingCallable<>(watched);

    ServerStreamingCallable<ReadChangeStreamRequest, ChangeStreamRecordT> readChangeStreamCallable =
        Callables.retrying(withBigtableTracer, innerSettings, clientContext);

    ServerStreamingCallable<ReadChangeStreamQuery, ChangeStreamRecordT>
        readChangeStreamUserCallable =
            new ReadChangeStreamUserCallable<>(readChangeStreamCallable, requestContext);

    SpanName span = getSpanName("ReadChangeStream");
    ServerStreamingCallable<ReadChangeStreamQuery, ChangeStreamRecordT> traced =
        new TracedServerStreamingCallable<>(
            readChangeStreamUserCallable, clientContext.getTracerFactory(), span);

    return traced.withDefaultCallContext(clientContext.getDefaultCallContext());
  }

  /**
   * Wraps a callable chain in a user presentable callable that will inject the default call context
   * and trace the call.
   */
  private <RequestT, ResponseT> UnaryCallable<RequestT, ResponseT> createUserFacingUnaryCallable(
      String methodName, UnaryCallable<RequestT, ResponseT> inner) {

    UnaryCallable<RequestT, ResponseT> traced =
        new TracedUnaryCallable<>(inner, clientContext.getTracerFactory(), getSpanName(methodName));

    return traced.withDefaultCallContext(clientContext.getDefaultCallContext());
  }

  private UnaryCallable<PingAndWarmRequest, PingAndWarmResponse> createPingAndWarmCallable() {
    UnaryCallable<PingAndWarmRequest, PingAndWarmResponse> pingAndWarm =
        GrpcRawCallableFactory.createUnaryCallable(
            GrpcCallSettings.<PingAndWarmRequest, PingAndWarmResponse>newBuilder()
                .setMethodDescriptor(BigtableGrpc.getPingAndWarmMethod())
                .setParamsExtractor(
                    new RequestParamsExtractor<PingAndWarmRequest>() {
                      @Override
                      public Map<String, String> extract(PingAndWarmRequest request) {
                        return ImmutableMap.of(
                            "name", request.getName(),
                            "app_profile_id", request.getAppProfileId());
                      }
                    })
                .build(),
            Collections.emptySet());
    return pingAndWarm.withDefaultCallContext(clientContext.getDefaultCallContext());
  }
  // </editor-fold>

  // <editor-fold desc="Callable accessors">
  /** Returns a streaming read rows callable */
  public ServerStreamingCallable<Query, Row> readRowsCallable() {
    return readRowsCallable;
  }

  /** Return a point read callable */
  public UnaryCallable<Query, Row> readRowCallable() {
    return readRowCallable;
  }

  public UnaryCallable<String, List<KeyOffset>> sampleRowKeysCallable() {
    return sampleRowKeysCallable;
  }

  public UnaryCallable<RowMutation, Void> mutateRowCallable() {
    return mutateRowCallable;
  }

  /**
   * Returns the callable chain created in {@link #createBulkMutateRowsCallable()} ()} during stub
   * construction.
   */
  public UnaryCallable<BulkMutation, Void> bulkMutateRowsCallable() {
    return bulkMutateRowsCallable;
  }

  /**
   * Returns the callable chain created in {@link #createCheckAndMutateRowCallable()} during stub
   * construction.
   */
  public UnaryCallable<ConditionalRowMutation, Boolean> checkAndMutateRowCallable() {
    return checkAndMutateRowCallable;
  }

  /**
   * Returns the callable chain created in {@link #createReadModifyWriteRowCallable()} ()} during
   * stub construction.
   */
  public UnaryCallable<ReadModifyWriteRow, Row> readModifyWriteRowCallable() {
    return readModifyWriteRowCallable;
  }

  /** Returns a streaming generate initial change stream partitions callable */
  public ServerStreamingCallable<String, ByteStringRange>
      generateInitialChangeStreamPartitionsCallable() {
    return generateInitialChangeStreamPartitionsCallable;
  }

  /** Returns a streaming read change stream callable. */
  public ServerStreamingCallable<ReadChangeStreamQuery, ChangeStreamRecord>
      readChangeStreamCallable() {
    return readChangeStreamCallable;
  }

  UnaryCallable<PingAndWarmRequest, PingAndWarmResponse> pingAndWarmCallable() {
    return pingAndWarmCallable;
  }
  // </editor-fold>

  private SpanName getSpanName(String methodName) {
    return SpanName.of(CLIENT_NAME, methodName);
  }

  @Override
  public void close() {
    for (BackgroundResource backgroundResource : clientContext.getBackgroundResources()) {
      try {
        backgroundResource.close();
      } catch (Exception e) {
        throw new IllegalStateException("Failed to close resource", e);
      }
    }
  }

  static class LoggerChain {
    private static final Logger LOGGER = Logger.getLogger(LoggerChain.class.getName());

    private List<UnaryRpcLogger<?, ?>> rpcLoggers = new ArrayList<>();

    void startLogging() {
      LOGGER.info(
          "Starting to monitor outstanding rpcs for mutateRows on thread: "
              + Thread.currentThread().getId());
      Thread thread =
          new Thread(
              () -> {
                while (true) {
                  try {
                    Thread.sleep(TimeUnit.MINUTES.toMillis(1));
                  } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                  }

                  for (UnaryRpcLogger<?, ?> rpcLogger : rpcLoggers) {
                    rpcLogger.logStale();
                  }
                }
              });

      thread.setName("checkAndMutate RPC counter thread");
      thread.setDaemon(true);
      thread.start();
    }

    <ReqT, RespT> UnaryCallable<ReqT, RespT> instrument(
        String label, UnaryCallable<ReqT, RespT> callable) {
      UnaryRpcLogger<ReqT, RespT> instrumented = new UnaryRpcLogger<>(label, callable);
      rpcLoggers.add(instrumented);
      return instrumented;
    }
  }

  static class UnaryRpcLogger<ReqT, RespT> extends UnaryCallable<ReqT, RespT> {
    private static final Logger LOGGER = Logger.getLogger(SafeResponseObserver.class.getName());
    private final String label;
    private final UnaryCallable<ReqT, RespT> next;
    private final ConcurrentHashMap<UUID, Instant> outstandingRpcs = new ConcurrentHashMap<>();

    UnaryRpcLogger(String label, UnaryCallable<ReqT, RespT> next) {
      this.label = label;
      this.next = next;
    }

    @Override
    public ApiFuture<RespT> futureCall(ReqT request, ApiCallContext context) {
      UUID uuid = UUID.randomUUID();
      outstandingRpcs.put(uuid, Instant.now());

      ApiFuture<RespT> f = next.futureCall(request, context);
      f.addListener(() -> outstandingRpcs.remove(uuid), MoreExecutors.directExecutor());
      return f;
    }

    public void logStale() {
      Instant now = Instant.now();
      int stuck = 0;
      for (Map.Entry<UUID, Instant> e : outstandingRpcs.entrySet()) {
        if (Duration.between(e.getValue(), now).compareTo(Duration.ofMinutes(1)) >= 0) {
          stuck++;
        }
      }
      if (stuck > 0) {
        LOGGER.info(String.format("[%s] Outstanding started RPCs: %d", label, stuck));
      }
    }
  }

  static class OutstandingRpcLogger implements ClientInterceptor {
    private static final Logger LOGGER = Logger.getLogger(SafeResponseObserver.class.getName());
    private static final AtomicLong channelCounter = new AtomicLong();
    private final long channelNum;
    private final ConcurrentHashMap<String, Instant> outstandingRpcs = new ConcurrentHashMap<>();

    public OutstandingRpcLogger() {
      channelNum = channelCounter.getAndIncrement();
      LOGGER.info("injecting grpc RPC tracker for channel " + channelNum);
    }

    public void startLogging() {
      Thread thread =
          new Thread(
              () -> {
                while (true) {
                  try {
                    Thread.sleep(TimeUnit.MINUTES.toMillis(1));
                  } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                  }

                  Instant now = Instant.now();
                  int stuck = 0;
                  List<String> stuckKeys = new ArrayList<>();
                  for (Map.Entry<String, Instant> e : outstandingRpcs.entrySet()) {
                    if (Duration.between(e.getValue(), now).compareTo(Duration.ofMinutes(1)) >= 0) {
                      stuck++;
                      stuckKeys.add(e.getKey());
                    }
                  }
                  if (stuck > 0) {
                    LOGGER.warning(
                        String.format("[%d] grpc Outstanding started RPCs: %d", channelNum, stuck));
                    LOGGER.warning(
                        String.format(
                            "[%d] stuck keys: %s", channelNum, String.join(",", stuckKeys)));
                  }
                }
              },
              "grpc-outstanding RPC counter thread");
      thread.setDaemon(true);
      thread.start();
    }

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(
        MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions, Channel channel) {
      String method = methodDescriptor.getBareMethodName();
      UUID uuid = UUID.randomUUID();
      String key = method + "-" + uuid;
      outstandingRpcs.put(key, Instant.now());
      return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(
          channel.newCall(methodDescriptor, callOptions)) {
        @Override
        public void start(Listener<RespT> responseListener, Metadata headers) {
          Listener<RespT> instrumentedListener =
              new ForwardingClientCallListener.SimpleForwardingClientCallListener<RespT>(
                  responseListener) {
                @Override
                public void onClose(io.grpc.Status status, Metadata trailers) {
                  outstandingRpcs.remove(key);
                  super.onClose(status, trailers);
                }
              };
          super.start(instrumentedListener, headers);
        }
      };
    }
  }
}
