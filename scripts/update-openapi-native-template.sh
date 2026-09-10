#!/usr/bin/env bash
set -euo pipefail

repo_root="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
version="${1:-}"

if [[ -z "${version}" ]]; then
  version="$(${PERL:-perl} -ne "if (/id 'org\.openapi\.generator' version '([^']+)'/) { print \$1; exit }" "${repo_root}/build.gradle")"
fi

if [[ -z "${version}" ]]; then
  printf 'Could not determine OpenAPI Generator version. Pass it explicitly, e.g. %s 7.11.0\n' "${0}" >&2
  exit 1
fi

template_path="Java/libraries/native/api.mustache"
template_url="https://raw.githubusercontent.com/OpenAPITools/openapi-generator/v${version}/modules/openapi-generator/src/main/resources/${template_path}"
output_dir="${repo_root}/build/openapi-generator-templates/libraries/native"
output_file="${output_dir}/api.mustache"
tmp_file="$(mktemp)"

cleanup() {
  rm -f "${tmp_file}"
}
trap cleanup EXIT

printf 'Fetching %s\n' "${template_url}"
curl --fail --location --silent --show-error "${template_url}" --output "${tmp_file}"

${PERL:-perl} -0pi -e '
  my $imports = "import java.time.Duration;\n";
  my $replacement_imports = "import java.time.Duration;\nimport java.nio.charset.StandardCharsets;\n";
  my $import_count = s/\Q$imports\E/$replacement_imports/g;

  my $decode_count = s/new String\(((?:response\.body\(\)|localVarResponse\.body\(\)|responseBody|localVarResponseBody)\.readAllBytes\(\))\)/new String($1, StandardCharsets.UTF_8)/g;
  my $remaining_default_charset_decode_count = () = /new String\((?:response\.body\(\)|localVarResponse\.body\(\)|responseBody|localVarResponseBody)\.readAllBytes\(\)\)/g;

  die "Expected exactly one StandardCharsets import insertion, got $import_count\n" unless $import_count == 1;
  die "Expected at least one UTF-8 response-body decode replacement, got $decode_count\n" unless $decode_count > 0;
  die "Expected no remaining default-charset response-body decodes, got $remaining_default_charset_decode_count\n" unless $remaining_default_charset_decode_count == 0;
' "${tmp_file}"

mkdir -p "${output_dir}"
mv "${tmp_file}" "${output_file}"
trap - EXIT

printf 'Updated %s from OpenAPI Generator %s\n' "${output_file#"${repo_root}/"}" "${version}"
