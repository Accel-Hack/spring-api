variable "app" {}
variable "aws" {}

terraform {
  required_version = "1.3.9"
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 4.27.0"
    }
  }
}

provider "aws" {
  region = var.aws.common.region
}

module "network" {
  source = "./module/network"

  name      = "${var.app.mode-st}-${var.app.name}"
  vpc_cidr  = var.aws.network.cidr_block
  azs       = var.aws.network.azs
  pub_cidrs = var.aws.network.public_subnet_cidrs
  pri_cidrs = var.aws.network.private_subnet_cidrs
}

module "rds" {
  source = "./module/rds"

  # values from tfvars
  name                  = var.app.name
  instance_class        = var.aws.rds.instance_class
  allocated_storage     = var.aws.rds.allocated_storage
  max_allocated_storage = var.aws.rds.max_allocated_storage
  db_name               = var.aws.rds.db_name
  username              = var.aws.rds.username
  password              = var.aws.rds.password
  # values from modules
  vpc_id                = module.network.vpc_id
  pri_subnet_ids        = module.network.pri_subnet_ids
}