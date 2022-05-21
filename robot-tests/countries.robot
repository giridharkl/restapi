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

Do a GET request to fetch a country using the api /v1/countries/1
    [Documentation]    Send a get request to fetch a country given id
    ${header}=  Create Dictionary    Authorization=${token}
    Create Session    alias=restSess     url=${url}    verify=True
    ${resp}=    GET On Session    restSess    url=/v1/countries/1    headers=${header}
    ${data}    Set Variable    ${resp.json()}
    Should Be Equal As Strings    USA    ${data['name']}

Do a GET request to fetch all countries using the api /v1/countries/
    [Documentation]    Send a get request to fetch all country
    ${header}=  Create Dictionary    Authorization=${token}
    Create Session    alias=restSess     url=${url}    verify=True
    ${resp}=    GET On Session    restSess    url=/v1/countries/    headers=${header}
    @{data}    Set Variable    ${resp.json()}
    ${dataSize}    Get Length     ${data}
    Should Be Equal As Integers    3    ${dataSize}

Do a DELETE request to delete a country using the api /v1/countries/3
    [Documentation]    Send a get request to delete a country
    ${header}=  Create Dictionary    Authorization=${token}
    Create Session    alias=restSess     url=${url}    verify=True
    ${resp}=    DELETE On Session    restSess    url=/v1/countries/3    headers=${header}
    Status Should Be     302    ${resp}

Do a POST request to add a continent using the api /v1/countries
    [Documentation]    Send a post request to add a country
    ${jdata}=    Catenate    { "name":"France" }
    ${header}=  Create Dictionary    Authorization=${token}    Content-Type=application/json
    Create Session    alias=restSess     url=${url}    verify=True
    ${resp}=    POST On Session    restSess    url=/v1/countries/    headers=${header}    data=${jdata}
    Status Should Be     302   ${resp}

Do a PUT request to update a country using the api /v1/countries
    [Documentation]    Send a post request to add a country
    ${jdata}=   Catenate     { "id":4, "name":"France New" }
    ${header}=  Create Dictionary    Authorization=${token}    Content-Type=application/json
    Create Session    alias=restSess     url=${url}    verify=True
    ${resp}=    PUT On Session    restSess    url=/v1/countries/    headers=${header}    data=${jdata}
    Status Should Be     302    ${resp}