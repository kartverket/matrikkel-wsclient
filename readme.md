### no.statkart.wsclient:matrikkel-wsclient

Web service integrasjoner mot eksterne systemer.
Disse er kompilert med Sun/Oracle sin JAXWS/HTTP Stack og krever ```com.sun.xml.ws:jaxws-rt``` på classpath (må legges til for tester - weblogic provider disse).

# Integrasjonstest
Denne testen verifiserer at implementert kontrakt for web service fungerer mot eksterne systemer.
Testene er skrevet med visse data og forutsetter at disse eksisterer i eksterne system.

Integrasjonstest kjøres lokalt via `gradlew testIntegration`. 

# IntelliJ
Oppsett fungerer med IntelliJ `2016.2`. 
Dersom en har en tidligere versjon så fungerer antakeligvis ikke kjøring av integrasjonstester ifra IntelliJ.


# Publisering
Pakkene ble tidligere publisert til Nexus. Tidligere pakker vil bli migrert til GitHub Packages.

Nye pakker publiseres til [GitHub Packages](https://github.com/orgs/kartverket/packages?repo_name=matrikkel-wsclient) via [build-push.yml](.github/workflows/build-publish.yml) workflowen.
Ved hver push til `master` så vil det bygges og publiseres en ny versjon av pakken.


# Releasetesting
Enhets-tester og integrasjons-tester kjøres automatisk ved PR og push til `master`.


# Versjonering
Pakkene har versjonsnummer som er av formatet `[Major version].[Date].[SHA]`
Alle pakker har samme versjon, og versjonsnummeret oppdateres ved hver publisering.


`[Major version]` oppdateres ved breaking changes og kan endres i [build-push.yml](.github/workflows/build-publish.yml) workflowen.
