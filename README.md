# RestConsumeJsonAPI

That Rest service consumes JSON with following format:  
[
    {
    "name":"task-1",
    "command":"touch /tmp/file1"
    },
    {
    "name":"task-3",
    "command":"echo 'Hello World!' > /tmp/file1"
    },
    {
    "name":"task-2",
    "command":"cat /tmp/file1"
    },
    {
    "name":"task-4",
    "command":"rm /tmp/file1"
    }
]

Sort Tasks and return following:    
[
    {
    "name":"task-1",
    "command":"touch /tmp/file1"
    },
    {
    "name":"task-3",
    "command":"echo 'Hello World!' > /tmp/file1"
    },
    {
    "name":"task-2",
    "command":"cat /tmp/file1"
    },
    {
    "name":"task-4",
    "command":"rm /tmp/file1"
    }
]

also you may consume it with following curl command:

curl -d @myTasks.json -H "Content-Type: application/json" http://localhost:8080/tasks
or 
curl -d @myTasks.json -H "Content-Type: application/json" http://localhost:8080/tasks | bash