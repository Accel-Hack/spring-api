variable "rds" {
  default = {
    engine         = "mysql"
    engine_version = "8.0"
  }
}

#--------------------------------------------------------------
# RDS
#--------------------------------------------------------------
resource "aws_db_instance" "default" {
  identifier            = var.name
  engine                = var.rds.engine
  engine_version        = var.rds.engine_version
  instance_class        = var.instance_class
  allocated_storage     = var.allocated_storage
  max_allocated_storage = var.max_allocated_storage
  db_name               = var.db_name
  username              = var.username
  password              = var.password
  parameter_group_name  = aws_db_parameter_group.default.name
  skip_final_snapshot   = true
  publicly_accessible   = true

  vpc_security_group_ids = [aws_security_group.rds-sg.id]
  db_subnet_group_name   = aws_db_subnet_group.rds-subnet-group.name

  tags = {
    Name = var.name
  }
}

#--------------------------------------------------------------
# Subnet group
#--------------------------------------------------------------

resource "aws_db_subnet_group" "rds-subnet-group" {
  name        = "${var.name}_pg"
  description = "rds subnet group for ${var.name}"
  subnet_ids  = var.pri_subnet_ids
}

#--------------------------------------------------------------
# Parameter group
#--------------------------------------------------------------
resource "aws_db_parameter_group" "default" {
  name   = "${var.name}-pg"
  family = "${var.rds.engine}${var.rds.engine_version}"

  parameter {
    name  = "character_set_server"
    value = "utf8mb4"
  }

  parameter {
    name  = "character_set_client"
    value = "utf8mb4"
  }

  parameter {
    name  = "time_zone"
    value = "Asia/Tokyo"
  }

  tags = {
    Name = var.name
  }
}

#--------------------------------------------------------------
# Security group
# https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/security_group#security_groups
#--------------------------------------------------------------
resource "aws_security_group" "rds-sg" {
  name        = "${var.name}_rds_sg"
  description = "RDS service security group for ${var.name}"
  vpc_id      = var.vpc_id

  ingress {
    from_port   = 3306
    to_port     = 3306
    protocol    = "tcp"
    #    cidr_blocks = ["10.0.0.0/16"]
    cidr_blocks = ["0.0.0.0/0"] # all traffic
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name = var.name
  }
}