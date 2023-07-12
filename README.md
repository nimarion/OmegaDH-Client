# OmegaDH-Client

TCP Client for processing data from the Omega ScanOVision Photofinish software and converting it to a comprehensible json object for distributing. Currently only prints the data to the console. Should distribute the data to other consumers in the future for ex. esp32 for a selfmade scoreboard. Maybe send to mqtt broker. 

Example output for the gemini protocol:
```json
{"type":"CLEAR_DISPLAY"}
{"time":"23:38:17","mode":0,"type":"RUNNING_TIME"}
{"time":"23:38:18","mode":0,"type":"RUNNING_TIME"}
{"time":"23:38:19","mode":0,"type":"RUNNING_TIME"}
{"time":"23:38:20","mode":0,"type":"RUNNING_TIME"}
{"time":"23:38:21","mode":0,"type":"RUNNING_TIME"}
{"type":"CLEAR_DISPLAY"}
{"line":1,"rank":1,"lane":4,"time":"11.62","type":"RESULT"}
{"text":"van der Weke","line":1,"type":"ALPHABETIC_LINE"}
{"line":1,"rank":2,"lane":5,"time":"11.79","type":"RESULT"}
{"text":"Nelson A","line":1,"type":"ALPHABETIC_LINE"}
{"line":1,"rank":3,"lane":3,"time":"11.79","type":"RESULT"}
{"text":"Awuah K","line":1,"type":"ALPHABETIC_LINE"}
{"type":"CLEAR_DISPLAY"}
```

Currently implemented Protocols:

- [x] Gemini
- [ ] Gemini Wind
- [x] Omega
- [x] Frontcamera

