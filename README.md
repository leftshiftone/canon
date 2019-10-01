[![CircleCI branch](https://img.shields.io/circleci/project/github/leftshiftone/canon/master.svg?style=flat-square)](https://circleci.com/gh/leftshiftone/canon)
[![GitHub tag (latest SemVer)](https://img.shields.io/github/tag/leftshiftone/canon.svg?style=flat-square)](https://github.com/leftshiftone/canon/tags)

# Canon

This library is used to transfer the XML or DSL based representations of the G.A.I.A. internal conversation ui model entities 
into a canonicalized format. Therefore the project contains the reader code to transform the model from the source format.

Available in [jcenter](https://bintray.com/leftshiftone/canon/canon.canon/_latestVersion). Can be included like `compile 'canon:canon:0.1.0'`.

````
XML
    \
      canonicalized format
    /
DSL
````


## Development

### Release
Releases are triggered locally. Just a tag will be pushed and CI pipelines take care of the rest.

#### Major
Run `./gradlew final -x bintrayUpload -Prelease.scope=major` locally.

#### Minor
Run `./gradlew final -x bintrayUpload -Prelease.scope=minor` locally.

#### Patch
Run `./gradlew final -x bintrayUpload -Prelease.scope=patch` locally.
