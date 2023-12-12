# ApiApplication

api application composed by SpringBoot and React

## How to run server.

### build database

check current directory

```bash
$ pwd
# ./ApiApplication
```

run docker database image

```bash
$ docker-compose up db -d
```

### run spring boot application

check current directory

```bash
$ pwd
# ./ApiApplication
```

run spring boot

```bash
$ ./gradlew bootRun -D"spring-boot.run.profiles"=local
```

- be sure Maven is installed.(If not install before)
- if you could not find accelhack packages `settings.xml` is required
- Error: `com.accelhack.accelparts:* was not found in https://repo.maven.apache.org/maven2 during a previous attempt. `

#### How to create setting.xml

Make the file(`settings.xml`) in the following path Mac:(`~/.m2/`), windows: (`C:\Users\%username%\.m2`). [※ not sure for windows]

Replace USERNAME with your github account and GITHUB_TOKEN with the NPM_TOKEN you used previously.

```settings.xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
    <servers>
        <server>
            <id>github</id>
            <username>${USERNAME}</username>
            <password>${GITHUB_TOKN}</password>
        </server>
    </servers>
    <profiles>
        <profile>
            <id>github</id>
            <repositories>
                <repository>
                    <id>central</id>
                    <url>https://repo1.maven.org/maven2</url>
                </repository>
                <repository>
                    <id>github</id>
                    <url>https://maven.pkg.github.com/accel-hack/accel-parts</url>
                    <snapshots>
                        <enabled>true</enabled>
                    </snapshots>
                </repository>
            </repositories>
        </profile>
    </profiles>
    <activeProfiles>
        <activeProfile>github</activeProfile>
    </activeProfiles>
</settings>
```

#### user info

| user role     | id      | pass   |
|---------------|---------|--------|
| administrator | system  | 123456 |
| manager       | manager | 123456 |
| user          | user    | 123456 |

## Code formatting

TBD

## How to run tests.

```bash
$ pwd
# ./ApiApplication

# full test
$ ./gradlew -D"user.timezone"=UTC test

# test for certain class
# CLASS_PATH → for certain class       ex) com.accelhack.application.api.app.mapper.SampleMapperSTest
# CLASS_PATH → for class under package ex) com.accelhack.application.api.app.mapper.**
$ ./gradlew -D"user.timezone"=UTC -D"test"="${CLASS_PATH}" test
```

when running a test witch uses the database. run the following command(be sure to have your .env file copied).

```bash
$ docker compose up test-db -d
``````
