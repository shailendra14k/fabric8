secure-soap: Demonstrates a Secure SOAP Web service
======================================================
Author: Fuse Team  
Level: Beginner  
Technologies: Camel, CXF, SOAP  
Summary: This quickstart demonstrates how to create a secure SOAP Web service with Apache CXF and expose it through the OSGi HTTP Service.  
Target Product: Fuse  
Source: <https://github.com/jboss-fuse/quickstarts>  

### How and What is secured

In this example we will be adding two interceptors to the inbound interceptor chain:
   - the CXF WSS4J interceptor to support WS-Security for passing along the credentials
   - a reference to the the JAAS authentication interceptor defined as a separate bean later on
     this will ensure that the credentials are being authenticated in the JAAS realm defined there ('karaf')

You can see the first interceptor [here](https://github.com/jboss-fuse/fabric8/blob/1.2.0.redhat-6-3-x/quickstarts/cxf/secure-soap/src/main/resources/OSGI-INF/blueprint/blueprint.xml#L42-L62) and the second [here](https://github.com/jboss-fuse/fabric8/blob/1.2.0.redhat-6-3-x/quickstarts/cxf/secure-soap/src/main/resources/OSGI-INF/blueprint/blueprint.xml#L72-L81). In this example the password is a simple PasswordText and this is not a good practice, don't try this in your production environment.

### Building this example

The example comes as source code and pre-built binaries with the fabric8 distribution. 

To try the example you do not need to build from source first. Although building from source allows you to modify the source code, and re-deploy the changes to fabric. See more details on the fabric8 website about the [developer workflow](http://fabric8.io/gitbook/developer.html).

To build from the source code:

1. Change your working directory to `quickstarts/cxf/secure-soap` directory.
1. Run `mvn clean install` to build the quickstart.

After building from the source code, you can upload the changes to the fabric container:

1. It is assumed that you have already created a fabric and are logged into a container called `root`.
1. Change your working directory to `quickstarts/cxf/secure-soap` directory.
1. Run `mvn fabric8:deploy` to upload the quickstart to the fabric container.

If you run the `fabric:deploy` command for the first then, it will ask you for the username and password to login the fabric container.
And then store this information in the local Maven settings file. You can find more details about this on the fabric8 website about the [Maven Plugin](http://fabric8.io/gitbook/mavenPlugin.html).


## How to run this example

The following information is divded into two sections, whether you are using the command line shell in fabric, or using the web console

### Using the command line shell

You can deploy and run this example at the console command line, as follows:

1. It is assumed that you have already created a fabric and are logged into a container called `root`.
1. Create a new child container and deploy the `quickstarts-cxf-secure.soap` profile in a single step, by entering the
 following command at the console:

        fabric:container-create-child --profile quickstarts-cxf.soap root mychild

1. Wait for the new child container, `mychild`, to start up. Use the `fabric:container-list` command to check the status of the `mychild` container and wait until the `[provision status]` is shown as `success`.
1. Log into the `mychild` container using the `fabric:container-connect` command, as follows:

        fabric:container-connect mychild

1. View the container log using the `log:tail` command as follows:

        log:tail

To exit the tail logger, press Ctrl-D. And to logout from the `mychild` container, then use the `exit` command, which returns back to the `root` container.

### Using the web console

You can deploy and run this example from the web console, as follows

1. It is assumed that you have already created a fabric and are logged into a container called `root`.
1. Login the web console
1. Click the Wiki button in the navigation bar
1. Select `quickstarts` --> `cxf` --> `secure-soap`
1. Click the `New` button in the top right corner
1. In the Create New Container page, enter `mychild` in the Container Name field, and click the *Create and start container* button


### How to try this example

Login to the web console and click the APIs button on the Runtime plugin, to show the SOAP services in the fabric

You can see details of the SOAP service by clicking the WSDL under the APIs column. 
The WSDL for the SOAP service is the `Location` url and append `?wsdl`


### To run a Web client:

You can use an external tool such as SoapUI to test web services.

When using SoapUI with WS Security, then configure the request properties as follows:

* Username = admin
* Password = admin
* Authentication Type = Global HTTP Settings
* WSS-Password Type = PasswordText


## Undeploy this example

The following information is divded into two sections, whether you are using the command line shell in fabric, or using the web console

### Using the command line shell

To stop and undeploy the example in fabric8:

1. Disconnect from the child container by typing Ctrl-D at the console prompt.
1. Stop and delete the child container by entering the following command at the console:

        fabric:container-stop mychild
        fabric:container-delete mychild

### Using the web console

To stop and undeploy the example in fabric8:

1. In the web console, click the *Runtime* button in the navigation bar.
1. Select the `mychild` container in the *Containers* list, and click the *Stop* button in the top right corner

### NOTES: 
If run this quickstart in standalone mode(no fabric8 server), need install cxf-ws-security feature first
        features:install cxf-ws-security 
