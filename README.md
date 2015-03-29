# LabKey Galaxy Interface
A Galaxy tool to query and update LabKey. 

## LabKey Setup

This tool assumes there is a project in LabKey called "CCC". Within this tool there is a list schema "CCC Data" that holds the test data stored in ref/cccdid_test.tsv.

## Galaxy Setup

Copy the labkey_interface directory to the $GALAXY_HOME/tools folder. In Galaxy's tool_conf.xml file, add the entry for the labkey_interface tool:

```xml
  <section id='labkey_interface' name='labkey_interface'>
    <tool file='labkey_interface/labkey_interface.xml'/>
  </section>
```

In order to execute a jar file in Galaxy, the absolute path to the tool needs to be specified in labkey_interface.xml. One alternative is to set a LABKEY_INTERFACE environmental variable in Galaxy's run.sh file.

This tool assumes there is a labkey instance running on the same server as the galaxy instance (ie. localhost). If you are running an instance of Galaxy that interacts with a job scheduler, add a section the the job_conf.xml file that tells Galaxy to run the labkey_interface tool at the location of your labkey server: https://wiki.galaxyproject.org/Admin/Config/Jobs#Mapping_Tools_To_Destinations.

## Development

The labkey_interface executable exsists as a jar file in order to package the required LabKey Java API. If you are running the query_labkey.java file, the LabKey API will need to be specified in your classpath. The LabKey Java API can be downloaded here: https://www.labkey.org/wiki/home/Documentation/page.view?name=javaAPI.