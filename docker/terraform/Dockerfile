FROM ubuntu:18.04

# prerequisite to install terraform
RUN apt-get update
RUN apt-get install -y wget

# install terraform
# ref) https://developer.hashicorp.com/terraform/tutorials/aws-get-started/install-cli#install-terraform
RUN apt-get install -y gnupg software-properties-common

RUN wget -O- https://apt.releases.hashicorp.com/gpg | \
    gpg --dearmor | \
    tee /usr/share/keyrings/hashicorp-archive-keyring.gpg

RUN gpg --no-default-keyring \
        --keyring /usr/share/keyrings/hashicorp-archive-keyring.gpg \
        --fingerprint

RUN echo "deb [signed-by=/usr/share/keyrings/hashicorp-archive-keyring.gpg] \
        https://apt.releases.hashicorp.com $(lsb_release -cs) main" | \
    tee /etc/apt/sources.list.d/hashicorp.list

RUN apt update && apt-get install terraform=1.3.9

RUN terraform -install-autocomplete

RUN bash -ic 'history -s terraform init' && \
    bash -ic 'history -s terraform fmt' && \
    bash -ic 'history -s terraform validate' && \
    bash -ic 'history -s terraform plan -out "./output/$(date +"%Y%m%d%H%I%")_plan"' && \
    bash -ic 'history -s terraform apply' && \
    bash -ic 'history -s terraform destroy'
