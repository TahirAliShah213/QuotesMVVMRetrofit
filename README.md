The QuotesMVVM project is an Android application developed using the Model-View-ViewModel (MVVM) architecture. The application leverages Retrofit for network operations and Room Library for local data storage. The main functionality of the application is to fetch and display quotes from the Quotable API (https://api.quotable.io/).

Key Features:
MVVM Architecture:

Ensures a clean separation of concerns.
Enhances testability and maintainability.
Utilizes ViewModel to manage UI-related data in a lifecycle-conscious way.
Retrofit for Network Requests:

Efficiently handles HTTP requests to fetch quotes.
Parses JSON responses from the Quotable API.
Provides a clean API for network operations.
Room Library for Local Storage:

Stores fetched quotes locally for offline access.
Utilizes SQLite database to cache data.
Provides a robust ORM layer to simplify database interactions.
LiveData and ViewModel:

Uses LiveData to observe data changes and update the UI accordingly.
ViewModel stores UI-related data, ensuring the data survives configuration changes.
Repository Pattern:

Centralizes data management.
Abstracts the data sources (network and local database) for better modularity.
Dependencies:
Retrofit: For making network calls and parsing JSON.
Room: For local database storage and management.
LiveData: For observing data changes and updating UI reactively.
ViewModel: For managing UI-related data in a lifecycle-aware manner.
API Integration:
Quotable API (https://api.quotable.io/): A free, open-source quotations API that provides quotes in JSON format.
Workflow:
Fetching Data: The application fetches quotes from the Quotable API using Retrofit.
Storing Data: The fetched quotes are stored locally using Room.
Displaying Data: The quotes are displayed in the UI, with options to navigate through them.
Updating Data: The UI is updated reactively through LiveData observers.
This project showcases the integration of modern Android development components to create a robust and responsive application for fetching and displaying quotes.
