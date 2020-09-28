### no.statkart.wsclient:matrikkel-wsclient

Web service integrasjoner mot eksterne systemer.
Disse er kompilert med Sun/Oracle sin JAXWS/HTTP Stack og krever ```com.sun.xml.ws:jaxws-rt``` på classpath (må legges til for tester - weblogic provider disse).

# Integrasjonstest
Denne testen verifiserer at implementert kontrakt for web service fungerer mot eksterne systemer.
Testene er skrevet med visse data og forutsetter at disse eksisterer i eksterne system.

Integrasjonstest kjøres lokalt via `gradlew testIntegration`. Merk at det er ikke alle integrasjonstester som 
kan kjøres lokalt da enkelte testmiljøer kun er tilgjengelige fra gitte ip-adresse på våre tjenere. Eksempel på 
dette er KontaktinformasjonClientTest mot brønnøysundregistrene. Denne kan kun kjøres fra tjenere som utgir seg for å ha
en gitt ip-adresse.

For jenkins se [pipeline konfigurasjon](config/jenkins-integration/Jenkinsfile).

# IntelliJ
Oppsett fungerer med IntelliJ `2016.2`. 
Dersom en har en tidligere versjon så fungerer antakeligvis ikke kjøring av integrasjonstester ifra IntelliJ.


# Versjonering og branching
Denne modulen har single branch oppsett og deployer versjoner fra trunk (master).
Test-versjoner publiseres automatisk med versjonsnummer etterfulgt av `-rc-X`; f.eks. `2.0-rc-3` 


# Publisering
Publish av ny versjon gjøres av jenkins jobb [`matrikkel-wsclient-publish`](config/jenkins-publish/Jenkinsfile). 
Jobben trigges manuelt og leser versjon fra [gradle properties](gradle.properties).