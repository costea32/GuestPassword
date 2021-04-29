{
#Generating a new password
local  pass  ([/tool  fetch  url="https://www.random.org/passwords/?num=1&len=8&format=plain&rnd=new" output=user as-value]->"data")
local trimPass [:pick $pass 0 8];
#Updating the security policy for guest configuration (replace guest with your security profile)
/caps-man security set guest passphrase=$trimPass

#Post request to guest password service
#replace the api and port as per your deployment,
#key should be set to the SERVICE_KEY provided to docker or the key provided in application.properties
local updPwdUrl "http://192.168.0.2:8013/password?key=secret123&password=$trimPass";
/tool fetch mode=http url=$updPwdUrl  http-method=post output=none;

#Optional send an email with the new wifi password
/tool e-mail send to="your@email.here" subject="Guest WiFi Password reset" \
body=$trimPass
}