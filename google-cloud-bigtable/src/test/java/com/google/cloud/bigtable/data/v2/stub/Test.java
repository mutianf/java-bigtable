package com.google.cloud.bigtable.data.v2.stub;

import com.google.api.core.SettableApiFuture;
import com.google.api.gax.grpc.GrpcStatusCode;
import com.google.api.gax.rpc.DeadlineExceededException;
import com.google.api.gax.rpc.ResponseObserver;
import com.google.api.gax.rpc.ServerStream;
import com.google.api.gax.rpc.StreamController;
import com.google.bigtable.v2.BigtableGrpc;
import com.google.cloud.bigtable.data.v2.BigtableDataClient;
import com.google.cloud.bigtable.data.v2.BigtableDataSettings;
import com.google.cloud.bigtable.data.v2.FakeServiceHelper;
import com.google.cloud.bigtable.data.v2.models.Query;
import com.google.cloud.bigtable.data.v2.models.Row;
import io.grpc.Server;
import io.grpc.Status;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

@RunWith(JUnit4.class)
public class Test {


    FakeService fakeService = new FakeService();

    FakeServiceHelper helper;

    int port;
    public void setup() throws IOException{
        helper = new FakeServiceHelper(fakeService);
        port = helper.getPort();
    }
    @org.junit.Test
    public void test() throws IOException, ExecutionException, InterruptedException {
        BigtableDataSettings.Builder settings = BigtableDataSettings.newBuilderForEmulator(port).setProjectId("p").setInstanceId("i")
                .setRefreshingChannel(false);

        BigtableDataClient client = BigtableDataClient.create(settings.build());

        SettableApiFuture future = SettableApiFuture.create();

        Iterator<Row> stream = client.readRowsCallable().call(Query.create("t")).iterator();

        Thread.sleep(1000);
        stream = client.readRowsCallable().call(Query.create("t")).iterator();

        System.out.println("Sent first call");
        client.readRowsCallable().call(Query.create("t"), new ResponseObserver<Row>() {
            @Override
            public void onStart(StreamController streamController) {
                streamController.cancel();
                System.out.println("hello");
            }

            @Override
            public void onResponse(Row row) {

            }

            @Override
            public void onError(Throwable throwable) {
                future.setException(throwable);
            }

            @Override
            public void onComplete() {
                future.set(null);
            }
        });
        System.out.println("Sent second call");
        try {
            future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(":(");
        Thread.sleep(60 * 1000);
    }

    class FakeService extends BigtableGrpc.BigtableImplBase {
        @Override
        public void readRows(
                com.google.bigtable.v2.ReadRowsRequest request,
                io.grpc.stub.StreamObserver<com.google.bigtable.v2.ReadRowsResponse> responseObserver) {
//            responseObserver.onError(Status.DEADLINE_EXCEEDED.asException());
            responseObserver.onCompleted();
        }
    }
}