import os
import boto3
import json

# Cognito Client
client = boto3.client('cognito-idp')

def updateUserProfile_handler(event, context):
    
    email = event['email']
    name = event['name']
    accessToken = event['accessToken']

    returnVal = {
        "isBase64Encoded": False,
        "statusCode": 200,
        "headers": {
            "Content-Type": "application/json"
        }
    }

    try:

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

        returnVal['body'] = json.dumps(response)

    except:

        message = "Unable to update user attributes"

        returnVal['statusCode'] = 503
        returnVal['body'] = json.dumps({
            "message": message
        })