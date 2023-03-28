#--------------------------------------------------------------
# General
#--------------------------------------------------------------
app = {
  name    = "sapp"
  mode    = "production"
  mode-st = "prod"
}

#--------------------------------------------------------------
# Aws
#--------------------------------------------------------------
aws = {
  common = {
    region = "ap-northeast-1"
  }

  #--------------------------------------------------------------
  # Network
  #--------------------------------------------------------------
  network = {
    cidr_block           = "10.0.0.0/16"
    azs                  = ["ap-northeast-1a", "ap-northeast-1c"]
    public_subnet_cidrs  = ["10.0.0.0/24", "10.0.1.0/24"]
    private_subnet_cidrs = ["10.0.2.0/24", "10.0.3.0/24"]
  }

  #--------------------------------------------------------------
  # Database
  #--------------------------------------------------------------
  rds = {
    # config
    instance_class        = "db.t4g.micro"
    # storage
    allocated_storage     = 50
    max_allocated_storage = 100 /* set max higher for auto scaling */
    # root user
    db_name               = "API_APPLICATION"
    username              = "foo"
    password              = "foobarbaz"
  }
}