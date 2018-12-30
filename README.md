Commands


/return result after 1s
```
curl --request GET \
  --url http://localhost:8080/deferredWithService \
  --header 'accept: application/json'
```

/return timed-out result after 5s
```
curl --request GET \
  --url http://localhost:8080/deferredWithServiceTimedOut \
  --header 'accept: application/json'
```

/return error
```
curl --request GET \
  --url http://localhost:8080/deferredWithServiceException \
  --header 'accept: application/json'
```
