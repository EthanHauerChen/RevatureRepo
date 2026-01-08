import {connection} from "./dbConnection"

db = connect(connection); //insert db string here

db.createCollection("users");

const calendarIds = db.calendars.find(
    {}, //filter by, in this case filter by nothing
    {_id: 1} //projection, get all _id from the result of this query
).toArray().map(c => c._id);

db.users.insertMany([
  {
    username: "alice_w",
    email: "alice.w@example.com",
    password: "alicePass123",
    calendarIds: [calendarIds[0], calendarIds[1]]
  },
  {
    username: "bob_smith",
    email: "bob.smith@example.com",
    password: "bobSecure456",
    calendarIds: [calendarIds[2]]
  },
  {
    username: "charlie_k",
    email: "charlie.k@example.com",
    password: "charliePwd789",
    calendarIds: [calendarIds[3], calendarIds[4]]
  },
  {
    username: "diana_p",
    email: "diana.p@example.com",
    password: "dianaPass321",
    calendarIds: []
  },
  {
    username: "ethan_r",
    email: "ethan.r@example.com",
    password: "ethanPwd654",
    calendarIds: [calendarIds[5]]
  },
  {
    username: "fiona_l",
    email: "fiona.l@example.com",
    password: "fionaPass987",
    calendarIds: [calendarIds[6], calendarIds[7]]
  },
  {
    username: "george_m",
    email: "george.m@example.com",
    password: "georgePwd111",
    calendarIds: [calendarIds[8]]
  },
  {
    username: "hannah_t",
    email: "hannah.t@example.com",
    password: "hannahPass222",
    calendarIds: [calendarIds[9]]
  },
  {
    username: "ivan_d",
    email: "ivan.d@example.com",
    password: "ivanPwd333",
    calendarIds: []
  },
  {
    username: "julia_n",
    email: "julia.n@example.com",
    password: "juliaPass444",
    calendarIds: [calendarIds[0]]
  }
]);