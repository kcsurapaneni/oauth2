#!/bin/sh

# https://developers.yubico.com/PIV/Guides/Generating_keys_using_OpenSSL.html
# Generate an RSA private key, of size 2048, and output it to a file named key.pem
openssl genrsa -out key.pem 2048
# Extract the public key from the key pair, which can be used in a certificate
openssl rsa -in key.pem -outform PEM -pubout -out public.pem

# https://github.com/koalaman/shellcheck/wiki/SC2035
mv -- *.pem src/main/resources/keys/
