import boto3
import json
import botocore

# Cognito Client
client = boto3.client('cognito-idp')

def changePassword_handler(event, context):
    
    jsonBody = json.loads(event['body'])
    
    oldPassword = jsonBody["oldPassword"]
    newPassword = jsonBody["newPassword"]
    accessToken = event['headers']['Access-Token']
    
    returnVal = {
        "isBase64Encoded": False,
        "statusCode": 200,
        "headers": {
            "Content-Type": "application/json"
        }
    }
    
    try:

        response = client.change_password(
            PreviousPassword = oldPassword,
            ProposedPassword = newPassword,
            AccessToken = accessToken
        )
        
        if response['ResponseMetadata']['HTTPStatusCode'] == 200:
            returnVal['body'] = json.dumps({
                "message": "Success"
            })

    except client.exceptions.InvalidPasswordException as invalidPasswordException:
        
        message = "Failed - Invalid password"

        returnVal['statusCode'] = 400
        returnVal['body'] = json.dumps({
            "message": message
        })
        
    except Exception as e:
        
        message = "Failed - Type: " + type(e).__name__
        returnVal['statusCode'] = 400
        returnVal['body'] = json.dumps({
            "message": message
        })
        
    return returnVal