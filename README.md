# Automwrite Take-Home Assessment

A Spring Boot application that processes user intent from text files and generates client-presentable recommendation letters in Word format.

## Overview

This application provides a REST API that:
1. Processes user intent from a `.txt` file
2. Integrates it with client and organization data from JSON files
3. Optionally uses Claude API for LLM-based text processing
4. Outputs a formatted `.docx` document using a provided template

## Prerequisites

- Java 17
- Gradle
- Claude API key (optional for LLM-based processing)

## Project Structure

```
src/main/
├── java/com/automwrite/assessment/
│   ├── config/           # Configuration classes
│   ├── controller/       # REST controllers
│   ├── model/           # Data models
│   │   ├── client/      # Client-related models
│   │   └── organization/ # Organization-related models
│   └── service/         # Business logic services
└── resources/
    ├── clientinfo/      # Client JSON data
    ├── orginfo/         # Organization JSON data
    ├── templates/       # Word document templates
    └── userintent/      # Sample user intent files
```

## Configuration

1. Set up environment variables:
   ```bash
   export CLAUDE_API_KEY=your_api_key_here
   ```

2. Configure application properties in `src/main/resources/application.properties`:
   ```properties
   server.port=8080
   client.json.path=src/main/resources/clientinfo/client.json
   organisation.json.path=src/main/resources/orginfo/org.json
   ```

## API Endpoints

### Process User Request
```
POST /api/user-request
Content-Type: multipart/form-data

Parameters:
- userIntent (file): The .txt file containing user intent
- template (file): The .docx template file

Response:
- Content-Type: application/octet-stream
- Body: Processed .docx document
```

## Example Usage

1. Prepare the user intent file (userintent.txt):
   ```
   I want to transfer my pension from Aviva to Fidelity.
   ```

2. Send the request:
   ```bash
   curl -X POST http://localhost:8080/api/user-request \
     -F "userIntent=@userintent.txt" \
     -F "template=@template.docx" \
     --output recommendation.docx
   ```

## Development

1. First time setup:
   ```bash
   # Make gradlew executable
   chmod +x gradlew
   ```

2. Build the project:
   ```bash
   ./gradlew build
   ```

3. Run tests:
   ```bash
   ./gradlew test
   ```

4. Run the application:
   ```bash
   ./gradlew bootRun
   ```

## Testing

The project includes unit tests for core functionality. Run tests with:
```bash
./gradlew test
```

## Dependencies

- Spring Boot 3.2.2
- Apache POI 5.2.5 for Word document processing
- Gson 2.10.1 for JSON processing
- Project Lombok for boilerplate reduction
- JUnit 5 for testing

## Error Handling

The application includes comprehensive error handling for:
- File processing errors
- JSON parsing errors
- Template processing errors
- LLM service errors

## Contact

For questions or support, please contact:
- Logan.Gibson@automwrite.co.uk
