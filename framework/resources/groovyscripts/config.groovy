environment = properties["build.environment"]
hybris_config_dir = properties["hybris.config.dir"]
ant.delete(dir:"${hybris_config_dir}", failonerror:false)
ant.mkdir(dir:"${hybris_config_dir}")

if(environment == "local") {
  ant.copy(todir:"${hybris_config_dir}", overwrite:true, failonerror:false) {
  	fileset(dir:"config/template", includes:"**/*")
  }
  ant.copy(todir:"${hybris_config_dir}", overwrite:true, failonerror:false) {
  	fileset(file:"config/${environment}/local.properties")
  }
  ant.concat(destfile:"${hybris_config_dir}/local.properties", append:true) {
    fileset(dir:"config", includes:"user.properties")
  }
}else if(environment.contains("hy")) {
  ant.copy(todir:"${hybris_config_dir}", overwrite:true, failonerror:false) {
  	fileset(file:"config/${environment}/customer.properties")
  }
  ant.copy(todir:"${hybris_config_dir}", overwrite:true, failonerror:false) {
  	fileset(file:"config/template/localextensions.xml")
  }
  ant.copy(todir:"${hybris_config_dir}", overwrite:true, failonerror:false) {
  	fileset(file:"config/${environment}/solrServerConfig.impex")
  }
  if (environment.contains("hyDEV")) {
	  ant.copy(todir:"${hybris_config_dir}/tomcat/conf", overwrite:true, failonerror:false) {
	  	fileset(file:"config/${environment}/tomcat/conf/server.xml")
	  }
  }
}else {
  ant.copy(todir:"${hybris_config_dir}", overwrite:true, failonerror:false) {
  	fileset(dir:"config/template", includes:"**/*")
  }
  ant.copy(todir:"${hybris_config_dir}", overwrite:true, failonerror:false) {
  	fileset(dir:"config/${environment}", includes:"**/*")
  }
}

