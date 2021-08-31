import os
import pymysql
import boto3
import json

# Configuration Values
endpoint = os.environ['RDS_ENDPOINT']
username = os.environ['RDS_USERNAME']
password = os.environ['RDS_PASSWORD']
database_name = os.environ['RDS_DATABASE']

# Connection
connection = pymysql.connect(host=endpoint, user=username, passwd=password, db=database_name)

# Cognito Client
client = boto3.client('cognito-idp')

def updateUserProfile_handler(event, context):
    
    email = event['email']
    name = event['name']
    accessToken = event['accessToken']

    response = client.update_user_attributes(
        UserAttributes = [
            {
                "Name": "email",
                "Value": email
            },
            {
                "Name": "name",
                "Value": name
            }
        ],
        AccessToken = accessToken
    )

    print(response)

    return {
        'statusCode': 200,
        'body': json.dumps('Completed function')
    }