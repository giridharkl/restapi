*** Settings ***
Documentation     RestAPI Test Suite
Library    RequestsLibrary
Library    JSONLibrary
Library    Collections

*** Variables ***
${url}=    http://localhost:8088
${auth_json}=    {"username":"admin","password":"admin"}

*** Test Cases ***
Do a POST request to get authorization token from the server
    [Documentation]     Send a post request body with username and password 
    ...    to fetch the valid jwt, parse the jwt to extract the token
    Create Session    alias=restSess     url=${url}    verify=True
    ${header}=  Create Dictionary    Content-Type=application/json
    ${resp}=    POST on Session      restSess    url=/auth     data=${auth_json}     headers=${header}
    ${jwt}      Set Variable    ${resp.json()}
    ${token}=    Set Variable    Bearer ${jwt['jwt']}
    Set Global Variable    ${token}

Do a GET request to fetch a continent using the api /v1/continents/1
    [Documentation]    Send a get request to fetch a continent given id
    ${header}=  Create Dictionary    Authorization=${token}
    Create Session    alias=restSess     url=${url}    verify=True
    ${resp}=    GET On Session    restSess    url=/v1/continents/1    headers=${header}
    ${data}    Set Variable    ${resp.json()}
    Should Be Equal As Strings    Asia    ${data['name']}

Do a GET request to fetch all continents using the api /v1/continents/
    [Documentation]    Send a get request to fetch all continents
    ${header}=  Create Dictionary    Authorization=${token}
    Create Session    alias=restSess     url=${url}    verify=True
    ${resp}=    GET On Session    restSess    url=/v1/continents/    headers=${header}
    @{data}    Set Variable    ${resp.json()}
    ${dataSize}    Get Length     ${data}
    Should Be Equal As Integers    7    ${dataSize}

Do a DELETE request to delete a continent using the api /v1/continents/3
    [Documentation]    Send a get request to delete a continent
    ${header}=  Create Dictionary    Authorization=${token}
    Create Session    alias=restSess     url=${url}    verify=True
    ${resp}=    DELETE On Session    restSess    url=/v1/continents/3    headers=${header}
    Status Should Be     302    ${resp}

Do a POST request to add a continent using the api /v1/continents
    [Documentation]    Send a post request to add a continent
    ${jdata}=    Catenate    { "name":"Antarctica" }
    ${header}=  Create Dictionary    Authorization=${token}    Content-Type=application/json
    Create Session    alias=restSess     url=${url}    verify=True
    ${resp}=    POST On Session    restSess    url=/v1/continents/    headers=${header}    data=${jdata}
    Status Should Be     302   ${resp}

Do a PUT request to update a continent using the api /v1/continents
    [Documentation]    Send a post request to add a continent
    ${jdata}=   Catenate     { "id":2, "name":"Africa New" }
    ${header}=  Create Dictionary    Authorization=${token}    Content-Type=application/json
    Create Session    alias=restSess     url=${url}    verify=True
    ${resp}=    PUT On Session    restSess    url=/v1/continents/    headers=${header}    data=${jdata}
    Status Should Be     302    ${resp}