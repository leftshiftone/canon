[![CircleCI branch](https://img.shields.io/circleci/project/github/leftshiftone/canon/master.svg?style=flat-square)](https://circleci.com/gh/leftshiftone/canon)
[![GitHub tag (latest SemVer)](https://img.shields.io/github/tag/leftshiftone/canon.svg?style=flat-square)](https://github.com/leftshiftone/canon/tags)
[![Bintray](https://img.shields.io/badge/dynamic/json.svg?label=bintray&query=name&style=flat-square&url=https%3A%2F%2Fapi.bintray.com%2Fpackages%2Fleftshiftone%2Fcanon%2Fone.leftshift.canon.canon%2Fversions%2F_latest)](https://bintray.com/leftshiftone/canon/one.leftshift.canon.canon/_latestVersion)

# Canon

This library is used to transfer the XML, DSL or ADOC based representations of the G.A.I.A. internal conversation ui model entities 
into a canonicalized format. Therefore the project contains the reader code to transform the model from the source format.

Available in [jcenter](https://bintray.com/leftshiftone/canon/one.leftshift.canon.canon/_latestVersion). Can be included like `compile 'one.leftshift.canon:canon:0.1.0'`.

````
XML
     \
DSL  -  canonicalized format
     /
ADOC
````


## Development

### Release
Releases are triggered locally. Just a tag will be pushed and CI pipelines take care of the rest.

#### Major
Run `./gradlew final -x sendReleaseEmail -Prelease.scope=major` locally.

#### Minor
Run `./gradlew final -x sendReleaseEmail -Prelease.scope=minor` locally.

#### Patch
Run `./gradlew final -x sendReleaseEmail -Prelease.scope=patch` locally.
