import os
import pymysql

# Configuration Values
endpoint = os.environ['RDS_ENDPOINT']
username = os.environ['RDS_USERNAME']
password = os.environ['RDS_PASSWORD']
database_name = os.environ['RDS_DATABASE']

# Connection
connection = pymysql.connect(host=endpoint, user=username, passwd=password, db=database_name)

def saveUsers_handler(event, context):
    
    email = event['request']['userAttributes']['email']
    username = event['userName']
    sql_query = "INSERT INTO Users (username, email, name) VALUES (%s, %s, null)"

    cursor = connection.cursor()
    cursor.execute(sql_query, (username, email))
    
    connection.commit()


    return event