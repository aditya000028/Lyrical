import os
import pymysql
import json

# Configuration Values
endpoint = os.environ['RDS_ENDPOINT']
username = os.environ['RDS_USERNAME']
password = os.environ['RDS_PASSWORD']
database_name = os.environ['RDS_DATABASE']

# Connection
connection = pymysql.connect(host=endpoint, user=username, passwd=password, db=database_name)

def getUserProfile_handler(event, context):

    returnVal = {
        "isBase64Encoded": False,
        "statusCode": 200,
        "headers": {
            "Content-Type": "application/json"
        }
    }

    username = event['pathParameters']['username']

    sql_query = "SELECT * FROM Users WHERE username = (%s)"

    try:
        cursor = connection.cursor()
        cursor.execute(sql_query, username)
        tuple = cursor.fetchone()

        if tuple is not None:
            email = tuple[1]
            name = tuple[2]

            user = {}

            user['username'] = username
            user['email'] = email

            if name is not None:
                user['name'] = name
            else:
                user['name'] = ""

            returnVal['body'] = json.dumps(user)

        else:
            returnVal['body'] = json.dumps("Username '" + username + "' does not exist in the database")

        return returnVal

    except:
        
        returnVal['statusCode'] = 503
        returnVal['body'] = json.dumps("Unable to connect to the database")
        return returnVal