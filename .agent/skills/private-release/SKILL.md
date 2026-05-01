---
name: private-release-java
description: >-
  Updates Maven version strings in the java-bigtable repository for private feature
  releases. Manages version bumps, snapshot generation, and synchronization across
  pom.xml, Version.java, and versions.txt. Use when cutting a new private release,
  updating snapshot versions for feature branches, or synchronizing emulator and
  client versions. Similar to Maven Release Plugin but tailored for internal
  private releases with specific suffix patterns.
---

# Private Release Java

This skill guides you through updating version strings in the `java-bigtable`
repository for private feature releases.

## Context

-   **Version Format**: `x.x.x-<feature>-SNAPSHOT` or `0.x.y-<feature>-SNAPSHOT`
    (emulator).
-   **Markers**: Look for `x-version-update` comments in `pom.xml` and specific
    blocks in `Version.java`.

## Procedure

### 1. Identify Target Version

-   **Feature Name**: Obtain the specific feature name from the user.
-   **Operation**:
    -   **Regular Update**: Set version to `[Matching-Base]-<feature>-SNAPSHOT`.
    -   **Release Cut**: Remove `-SNAPSHOT` from the current feature version.
    -   **Next Snapshot**: Bump patch version and add `-<feature>-SNAPSHOT`.
-   **Workflow Note**: Perform **Release Cut** first. Proceed to **Next Snapshot
    Generation** only when explicitly requested.

### 2. Update `pom.xml` Files

Search for `{x-version-update` markers in `pom.xml` files. Update the preceding
or containing version tag.
- Client Base: e.g., `2.75.3`
- Emulator Base: e.g., `0.212.3`

### 3. Update `Version.java`

File: `google-cloud-bigtable/src/main/java/com/google/cloud/bigtable/Version.java`
Update the `VERSION` string within the
`{x-version-update-start:google-cloud-bigtable:current}` block.

### 4. Update Dependencies

Check `test-proxy/pom.xml` and ensure all internal version properties are
synchronized.

### 5. Update `versions.txt`

Update the `current-version` column (last column) for all listed modules,
ensuring both client and emulator versions reflect the new feature string.

### 6. Configure Artifact Registry Extension

Create a `.mvn/extensions.xml` file in the project root to enable the Artifact
Registry wagon:

```xml
<extensions xmlns="http://maven.apache.org/EXTENSIONS/1.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/EXTENSIONS/1.0.0 http://maven.apache.org/xsd/core-extensions-1.0.0.xsd">
  <extension>
      <groupId>com.google.cloud.artifactregistry</groupId>
      <artifactId>artifactregistry-maven-wagon</artifactId>
      <version>2.2.5</version>
  </extension>
</extensions>
```

### 7. Configure Distribution and Repositories

In `pom.xml`, `google-cloud-bigtable-bom/pom.xml`, and
`google-cloud-bigtable-deps-bom/pom.xml`, add the following sections. Replace
`{feature_name}` with the target feature name:

```xml
<distributionManagement>
    <snapshotRepository>
        <id>artifact-registry</id>
        <url>artifactregistry://us-maven.pkg.dev/cloud-bigtable-ecosystem/{feature_name}</url>
    </snapshotRepository>
    <repository>
        <id>artifact-registry</id>
        <url>artifactregistry://us-maven.pkg.dev/cloud-bigtable-ecosystem/{feature_name}</url>
    </repository>
</distributionManagement>

<repositories>
    <repository>
        <id>artifact-registry</id>
        <url>artifactregistry://us-maven.pkg.dev/cloud-bigtable-ecosystem/{feature_name}</url>
        <releases>
            <enabled>true</enabled>
        </releases>
        <snapshots>
            <enabled>true</enabled>
        </snapshots>
    </repository>
</repositories>
```

### 8. Enable Source Publishing

Add the `maven-source-plugin` to the main `pom.xml` to ensure `-sources` jars
are published:

```xml
<plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-source-plugin</artifactId>
  <version>3.3.1</version>
  <executions>
      <execution>
          <id>attach-sources</id>
          <phase>verify</phase>
          <goals>
              <goal>jar-no-fork</goal>
          </goals>
      </execution>
  </executions>
</plugin>
```

## Verification

-   Run: `mvn clean install -DskipTests`
-   Check `VersionTest.java` for regex compliance with the new format.

