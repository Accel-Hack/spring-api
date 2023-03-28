variable "name" {}

variable "db_name" {}

variable "username" {}

variable "password" {}

variable "instance_class" {}

variable "allocated_storage" {}

variable "max_allocated_storage" {}

variable "vpc_id" {}

variable "pri_subnet_ids" { type = list(string) }
