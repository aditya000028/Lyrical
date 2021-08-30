import os
import pymysql
import boto3

# Configuration Values
endpoint = os.environ['RDS_ENDPOINT']
username = os.environ['RDS_USERNAME']
password = os.environ['RDS_PASSWORD']
database_name = os.environ['RDS_DATABASE']

# Connection
connection = pymysql.connect(host=endpoint, user=username, passwd=password, db=database_name)

# Cognito Client
client = boto3.client('cognito-idp')

def getUserProfile_handler(event, context):
    return