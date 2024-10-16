# User Guide

## Product Description
This product aims to streamline the tracking of internship applications for the user, where the user is able to store key details of the internship as well as save and edit the status of the application.

**By using this tool, the user can:**

**Store Important seedu.duke.Internship Information:** Users can input and store key details such as the role, company, duration, required skills, application deadlines, and any additional notes.

**Track Application Status:** Users can save the current status of their application (e.g., not applied, applied, interview scheduled, offer received) and update it as the application progresses.

**Edit and Manage Internships:** Users have the ability to edit saved internships as needed, modify statuses, update deadlines, or remove outdated internships from the list.

**Filter Internships by Key Criteria:** The user can filter the tracked internships by relevant skills, application status, or upcoming deadlines, allowing for better organization and prioritization.

**Monitor Deadlines Easily:** The tool helps ensure users stay on top of their internship deadlines by flagging or sorting applications with approaching deadlines.


## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 17 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

# Features

## Add Command
`add`

Adds internship to the list of internships currently being tracked by the user. The information added is automatically saved to a .txt file. Each internship you add is assigned a unique ID for editing purposes, which is generated based on the order in which these internships were created and stored.


This function can be expanded upon to add different features to each internship.

**Format:**

`add -name {Role name} -company {Company name} // default param`
`-duration /from {start date} /to {end date} // additional params`

**Example Input 1:**
`add -name Software Engineer Intern -company Google`

**Example Output 1:**
```
ID: 01
Role: Software Engineer Intern
Company: Google
Duration: Not Stated
Skills: Not Stated 
```

**Example Input 2:**
`add -name Embedded Software Engineer Intern -company Continental -duration /from 05/25 /to 08/25`

**Example Output 2:**
```
ID: 02
Role: Embedded Software Engineer Intern
Company: Continental
Duration: 05/25 to 08/25
Skills: Not Stated
```

## Update Command
`update`

This feature allows you to update any field of an internship application. For example, when updating the status of an internship application, you can mark the status as ‘application pending’, ‘application completed’, ‘accepted’, ‘rejected’ etc.

The default status for any internship that is stored in the database is ‘application pending’.

**Format:**
`update {ID} {-field} {updated information}`

**Example:**

`update 02 -status application completed`

```
ID: 02								 **Status**
Role: Embedded Software Engineer Intern    		application completed
Company: Continental
Duration: 05/25 to 08/25
Skills: Not Stated
```

`Update 02 -status interview scheduled`

```
ID: 02								 Status
Role: Embedded Software Engineer Intern    		interview scheduled
Company: Continental
Duration: 05/25 to 08/25
Skills: Not Stated
```

`Update 02 -company Venti`

```
ID: 02								 Status
Role: Embedded Software Engineer Intern    		interview scheduled
Company: Venti
Duration: 05/25 to 08/25
Skills: Not Stated
```

`Update 02 -duration /from 04/25`

```
ID: 02								 Status
Role: Embedded Software Engineer Intern    		interview scheduled
Company: Venti
Duration: 04/25 to 08/25
Skills: Not Stated
```

`update 02 -skills Python SQL`

```
ID: 02								 Status
Role: Embedded Software Engineer Intern    		interview scheduled
Company: Venti
Duration: 04/25 to 08/25
Skills: Python, SQL
```
## Sort Command
`sort`

This feature allows you to sort and list all the internships saved in your database. The default display format is in increasing order of their ID’s (based on time of creation)

Users can specify a flag to display them in alphabetical order (regardless of uppercase or lowercase) of their role titles or by deadline.

**Format:**


`sort {field}`

**Example:**

`sort -deadline`

```
ID: 02
Role: Software Engineer Intern
Company: Google
Duration: 05/23 to 08/25
Skills: Not Stated

ID: 01
Role: Embedded Software Engineer Intern
Company: Continental
Duration: 05/24 to 08/24
Skills: Not Stated
```
`sort -alphabet`

```
ID: 02
Role: Embedded Software Engineer Intern
Company: Continental
Duration: 05/25 to 08/25
Skills: Not Stated

ID: 01
Role: Software Engineer Intern
Company: Google
Duration: Not Stated
Skills: Not Stated
```
## Filter command: 
`filter`

This feature is an extension of the previous list feature. It allows the user to specify certain values for any of the role name, company name or duration parameters based on which only the relevant internships are shown. 

The role and company name parameters are case-insensitive, For the duration parameter, this feature displays all relevant internships whose start dates and end dates are within the duration timeline specified by the user.  For the MVP version of the product, user can only sort by 1 field at a time.

**Format:**

`filter -role {Role name} -`

`filter -company {Company name}`

`filter -duration /from {start date} /to {end date}`

`filter -skills {skill}`

`filter -deadline /by {date}`

**Example:**

`list`

```
ID: 01
Role: Software Engineer Intern
Company: Google
Duration: Not Stated
Skills: Not Stated

ID: 02
Role: Embedded Software Engineer Intern
Company: Continental
Duration: 05/25 to 08/25
Skills: Not Stated
```
`filter -company Google`

```
ID: 01
Role: Software Engineer Intern
Company: Google
Duration: Not Stated
Skills: Not Stated
```

`filter -duration /from 04/25 /to 09/25`

```
ID: 02
Role: Embedded Software Engineer Intern
Company: Continental
Duration: 05/25 to 08/25
Skills: Not Stated
```

## Delete Command: 
`del`

This feature removes an entire listing from the tracker.

**Format:** `del {ID}`

**Example:**

`list`

```
ID: 01
Role: Embedded Software Engineer Intern
Company: Continental
Duration: 05/25 to 08/25
Skills: Not Stated

ID: 02
Role: Software Engineer Intern
Company: Google
Duration: Not Stated
Skills: Not Stated
```

`del {01}`

```
You have deleted this listing:

ID: 01
Role: Embedded Software Engineer Intern
Company: Continental
Duration: 05/25 to 08/25
Skills: Not Stated
```

`list`

```
ID: 02
Role: Software Engineer Intern
Company: Google
Duration: Not Stated
Skills: Not Stated
```

## Help Command: 
`help`

Shows the commands available for the user.
Describes the available functions and inputs that are needed
Other basic commands.

**Format:** `/help`

**Example Output**

```
    add -name {Role name} -company {Company name}: Adds internship to the list of internships currently being tracked by the user.


    update {ID} {-field} {updated information}: Adds information to the field of the entry tagged to the ID.
    Eg. update 02 -status application completed
```


## Exit Command

Terminates the program.

**Format:** `/exit`



## Invalid Inputs
Shows whenever an incomplete or invalid search call is performed. Output also shows what corrective action can be taken.

Example Output:
Please include a duration if you are using the -duration flag!
Eg. Update 02 -duration /from 04/25

Please include specific skills if you are using the -skills flag!

I do not understand what flag you are using, type /help to get a list of valid commands.






## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
