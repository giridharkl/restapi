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

Do a GET request to fetch a continent using the api /v1/cities/1
    [Documentation]    Send a get request to fetch a continent given id
    ${header}=  Create Dictionary    Authorization=${token}
    Create Session    alias=restSess     url=${url}    verify=True
    ${resp}=    GET On Session    restSess    url=/v1/cities/1    headers=${header}
    ${data}    Set Variable    ${resp.json()}
    Should Be Equal As Strings    Helsinki    ${data['name']}

Do a GET request to fetch all cities using the api /v1/cities/
    [Documentation]    Send a get request to fetch all cities
    ${header}=  Create Dictionary    Authorization=${token}
    Create Session    alias=restSess     url=${url}    verify=True
    ${resp}=    GET On Session    restSess    url=/v1/cities/    headers=${header}
    @{data}    Set Variable    ${resp.json()}
    ${dataSize}    Get Length     ${data}
    Should Be Equal As Integers    3    ${dataSize}

Do a POST request to add a city using the api /v1/cities
    [Documentation]    Send a post request to add a city
    ${jdata}=    Catenate    { "name":"Paris", "countryId":4, "continentId":5 }
    ${jcountry}=    Catenate    {"name":"France"}
    ${header}=  Create Dictionary    Authorization=${token}    Content-Type=application/json
    Create Session    alias=restSess     url=${url}    verify=True
    ${resp}=    POST On Session    restSess    url=/v1/countries/    headers=${header}    data=${jcountry}
    ${resp}=    POST On Session    restSess    url=/v1/cities/    headers=${header}    data=${jdata}
    Status Should Be     302   ${resp}

Do a PUT request to update a city using the api /v1/cities
    [Documentation]    Send a post request to add a city
    ${jdata}=   Catenate     { "id":2, "name":"Munich", "continentId":5, "countryId":3 }
    ${header}=  Create Dictionary    Authorization=${token}    Content-Type=application/json
    Create Session    alias=restSess     url=${url}    verify=True
    ${resp}=    PUT On Session    restSess    url=/v1/cities/    headers=${header}    data=${jdata}
    Status Should Be     302    ${resp}

Do a GET request to fetch all cities that belong to a country api /v1/cities/country/1
    [Documentation]    Send a get request to fetch all cities for a given country
    ${header}=  Create Dictionary    Authorization=${token}
    Create Session    alias=restSess     url=${url}    verify=True
    ${resp}=    GET On Session    restSess    url=/v1/cities/country/1    headers=${header}
    @{data}    Set Variable    ${resp.json()}
    ${dataSize}    Get Length     ${data}
    Should Be Equal As Integers    1    ${dataSize}

Do a GET request to fetch all cities that belong to a continent api /v1/cities/continent/5
    [Documentation]    Send a get request to fetch all cities for a given continent
    ${header}=  Create Dictionary    Authorization=${token}
    Create Session    alias=restSess     url=${url}    verify=True
    ${resp}=    GET On Session    restSess    url=/v1/cities/continent/5    headers=${header}
    @{data}    Set Variable    ${resp.json()}
    ${dataSize}    Get Length     ${data}
    Should Be Equal As Integers    3    ${dataSize}

Do a DELETE request to delete a city using the api /v1/cities/3
    [Documentation]    Send a get request to delete a city
    ${header}=  Create Dictionary    Authorization=${token}
    Create Session    alias=restSess     url=${url}    verify=True
    ${resp}=    DELETE On Session    restSess    url=/v1/cities/3    headers=${header}
    Status Should Be     302    ${resp}