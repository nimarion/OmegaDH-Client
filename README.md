# OmegaDH-Client

Client for reading data from the Omega ScanOVision Photofinish software, Sirocco anemometer and Omega LapCounter for further processing.

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

- [x] Gemini (TCP)
- [x] Gemini Wind (TCP)
- [x] Omega (TCP)
- [x] Frontcamera (TCP)
- [x] Sirocco (Serial) 
- [x] LapCounter (Serial) 
- [x] Startliste File Parser (CSV)
