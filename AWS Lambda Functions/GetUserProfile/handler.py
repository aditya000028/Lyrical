import json
import boto3

client = boto3.client('cognito-idp')

def getUserProfile_handler(event, context):

    returnVal = {
        "isBase64Encoded": False,
        "statusCode": 200,
        "headers": {
            "Content-Type": "application/json"
        }
    }

    accessToken = event['accessToken']

    try:
        response = client.get_user(
            AccessToken = accessToken
        )

        returnVal['body'] = json.dumps(response)

    except:
        returnVal['statusCode'] = 503
        returnVal['body'] = json.dumps("Unable to retrieve the user")

    return returnVal