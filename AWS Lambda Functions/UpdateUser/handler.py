import os
import boto3
import json

# Cognito Client
client = boto3.client('cognito-idp')

def updateUserProfile_handler(event, context):
    
    jsonBody = json.loads(event['body'])
    
    email = jsonBody["email"]
    name = jsonBody["name"]
    accessToken = event['headers']['Access-Token']
    
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
        
        if response['ResponseMetadata']['HTTPStatusCode'] == 200:
            returnVal['body'] = json.dumps({
                "message": "Success"
            })

    except Exception as e:
        
        message = "Failed - Type: " + type(e).__name__

        returnVal['statusCode'] = 400
        returnVal['body'] = json.dumps({
            "message": message
        })
        
    return returnVal