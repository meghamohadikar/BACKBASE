# ubp-pb360poc-authentication-service

Authentication service for PB360 POC

## Configuration

You need to configure some environment variables to run this service:

- KERBEROS_AD_DOMAIN: 
Domain of the Active Directory server 
(default: UBPDEV.LOCAL)

- KERBERORS_AD_SERVER:
LDAP url
(default: ldap://194.3.235.134:389)

- KERBEROS_SERVICE_PRINCIPAL:
Service principal of the application host
(default: HTTP/macbookpro@UBPDEV.LOCAL)

- KERBEROS_CLASSPATH_RESOURCES:
Use classpath resources for keytab and krb5 configuration file
(default: true)

- KERBEROS_KEYTAB_LOCATION:
Location of the keytab to authenticate to the AD
(default: tomcat.keytab)

- KERBEROS_KRB5INI_LOCATION:
Location of the krb5 configuration file
(default: krb5.conf)

- KERBEROS_DEBUG_MODE:
Enable the debug mode of the kerberos authentication
(default: true)

- KERBEROS_LDAP_SEARCH_BASE:
LDAP search base
(default: dc=ubpdev,dc=local)

- KERBEROS_LDAP_SEARCH_FILTER:
LDAP search filter
(default: (cn={0},ou=Employees))