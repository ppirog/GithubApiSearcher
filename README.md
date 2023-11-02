# GithubApiSearcher
REST excercise
Acceptance criteria:

FIRST
As an api consumer, given username (ppirog) and header “Accept: application/json”, 
List all his github repositories, which are not forks. 

Information, which I require in the response, is:
Repository Name
Owner Login
For each branch it’s name and last commit sha
![image](https://github.com/ppirog/GithubApiSearcher/assets/126290295/f829ccd8-96b2-4501-be9c-ea2281e3417a)



SECOND
As an api consumer, given not existing github user, I would like to receive 404 response in such a format:


    {
    “status”: ${responseCode},    
    “Message”: ${whyHasItHappened}     
    }
![image](https://github.com/ppirog/GithubApiSearcher/assets/126290295/8d82cfcd-6a7e-411f-8450-88567934e7ec)

THIRD
As an api consumer, given header “Accept: application/xml”, I would like to receive 406 response in such a format:
    
    {
    “status”: ${responseCode},   
    “Message”: ${whyHasItHappened} 
    }

![image](https://github.com/ppirog/GithubApiSearcher/assets/126290295/b19f85d7-2bf6-4937-ac82-747d12c37325)




