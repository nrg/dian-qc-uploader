dian-qc-uploader
================

Developed by the `Neuroinformatics Research Group <http://nrg.wustl.edu>`_ for
uploading quality control data for the DIAN study to the `CNDA 
<http://cnda.wustl.edu>`_

Documentation: http://johnpaulett.github.com/dian-qc-uploader/
Source: http://github.com/johnpaulett/dian-qc-uploader
Issues: http://github.com/johnpaulett/dian-qc-uploader/issues

Building From Source
--------------------

## Generate the executable JAR
mvn clean assembly:assembly

## Build the documentation (builds in ../dian-qc-uploader-site, which is the 
## gh-pages git branch of the project)
mvn site:deploy
