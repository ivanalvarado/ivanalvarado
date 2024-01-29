#!/usr/bin/env sh

set -euo pipefail

./gradlew run --args="--api-token $GH_API_TOKEN --login ivanalvarado --output-file README.md"
