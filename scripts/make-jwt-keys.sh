#!/usr/bin/env bash
# Creates the RS256 key pair used to sign access tokens (PKCS#8, 2048-bit).
# Usage: scripts/make-jwt-keys.sh [output-dir]   (default: secrets)
set -euo pipefail
dir="${1:-secrets}"
mkdir -p "$dir"
openssl genpkey -algorithm RSA -pkeyopt rsa_keygen_bits:2048 -out "$dir/jwt-private.pem"
openssl pkey -in "$dir/jwt-private.pem" -pubout -out "$dir/jwt-public.pem"
chmod 600 "$dir/jwt-private.pem"
echo "JWT_PRIVATE_KEY_LOCATION=$(cd "$dir" && pwd)/jwt-private.pem"
echo "JWT_PUBLIC_KEY_LOCATION=$(cd "$dir" && pwd)/jwt-public.pem"
