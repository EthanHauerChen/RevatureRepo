db = connect('mongodb://127.0.0.1:27017/test'); //insert db string here

db.createCollection("calendars");
db.calendars.insertMany([
    {
        "name": "Work"
    },
    {
        "name": "Personal"
    },
    {
        "name": "Family"
    },
    {
        "name": "Fitness"
    },
    {
        "name": "Travel"
    },
    {
        "name": "School"
    },
    {
        "name": "Project Alpha"
    },
    {
        "name": "Meetings"
    },
    {
        "name": "Deadlines"
    },
    {
        "name": "Holidays"
    }
]);