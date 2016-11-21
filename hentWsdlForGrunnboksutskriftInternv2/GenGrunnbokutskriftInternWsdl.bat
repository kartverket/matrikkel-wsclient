call SetWsdlUrl
echo Maskin: %WsdlUrl%
echo Lagre WDL: %WsdlSave%
%JAVA_HOME%/bin/java -cp . LesInnWsdlTilResorces "%WsdlUrl%/GrunnboksutskriftInternServiceWS?WSDL" "%WsdlSave%"
