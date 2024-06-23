import cv2
import face_recognition

# Load your image (for recognition)
your_image = face_recognition.load_image_file("your_image.jpg")
your_encoding = face_recognition.face_encodings(your_image)[0]

# Initialize some variables
face_locations = []
face_encodings = []

# Initialize the video capture
cap = cv2.VideoCapture(0)  # Use 0 for the default camera

while True:
    # Grab a single frame of video
    ret, frame = cap.read()

    # Convert the image from BGR color (which OpenCV uses) to RGB color (which face_recognition uses)
    rgb_frame = frame[:, :, ::-1]

    # Find all the faces and face encodings in the current frame of video
    face_locations = face_recognition.face_locations(rgb_frame)
    face_encodings = face_recognition.face_encodings(rgb_frame, face_locations)

    # Display the results
    for (top, right, bottom, left), face_encoding in zip(
        face_locations, face_encodings
    ):
        # See if the face is a match for the known face(s)
        matches = face_recognition.compare_faces([your_encoding], face_encoding)
        name = "Unknown"

        if not matches[0]:
            # Draw a box around the face
            cv2.rectangle(frame, (left, top), (right, bottom), (0, 0, 255), 2)

    # Display the resulting image
    cv2.imshow("Video", frame)

    # Hit 'q' on the keyboard to quit!
    if cv2.waitKey(1) & 0xFF == ord("q"):
        break

# Release handle to the webcam
cap.release()
cv2.destroyAllWindows()
