# GithubApiSearcher
CRUD excercise
Acceptance criteria:
FIRST
As an api consumer, given username (kalqa) and header “Accept: application/json”, I would like to list all his github repositories, which are not forks. 

Information, which I require in the response, is:
Repository Name
Owner Login
For each branch it’s name and last commit sha


SECOND
As an api consumer, given not existing github user, I would like to receive 404 response in such a format:

{
    “status”: ${responseCode},
    “Message”: ${whyHasItHappened} 
}
THIRD
As an api consumer, given header “Accept: application/xml”, I would like to receive 406 response in such a format:

{
    “status”: ${responseCode},
    “Message”: ${whyHasItHappened} 
}
