import cv2
import torch

model = torch.hub.load(
    "/Users/janek/dev/yolov7",
    "custom",
    "/Users/janek/dev/yolov7/yolov7.pt",
    source="local",
    force_reload=True,
)

model.eval()

COCO_CLASSES = [
    "person",
    "bicycle",
    "car",
    "motorcycle",
    "airplane",
    "bus",
    "train",
    "truck",
    "boat",
    "traffic light",
    "fire hydrant",
    "stop sign",
    "parking meter",
    "bench",
    "bird",
    "cat",
    "dog",
    "horse",
    "sheep",
    "cow",
    "elephant",
    "bear",
    "zebra",
    "giraffe",
    "backpack",
    "umbrella",
    "handbag",
    "tie",
    "suitcase",
    "frisbee",
    "skis",
    "snowboard",
    "sports ball",
    "kite",
    "baseball bat",
    "baseball glove",
    "skateboard",
    "surfboard",
    "tennis racket",
    "bottle",
    "wine glass",
    "cup",
    "fork",
    "knife",
    "spoon",
    "bowl",
    "banana",
    "apple",
    "sandwich",
    "orange",
    "broccoli",
    "carrot",
    "hot dog",
    "pizza",
    "donut",
    "cake",
    "chair",
    "couch",
    "potted plant",
    "bed",
    "dining table",
    "toilet",
    "tv",
    "laptop",
    "mouse",
    "remote",
    "keyboard",
    "cell phone",
    "microwave",
    "oven",
    "toaster",
    "sink",
    "refrigerator",
    "book",
    "clock",
    "vase",
    "scissors",
    "teddy bear",
    "hair drier",
    "toothbrush",
]

# Start video capture from the laptop camera
cap = cv2.VideoCapture(0)
n = 15
nth_frame = None
while n > 0:
    ret, nth_frame = cap.read()
    if ret:
        n -= 1

while True:

    # Capture frame-by-frame
    ret, frame = cap.read()
    results = model(frame)
    person_detected = False
    phone_detected = False

    for result in results.pred:
        for det in result:
            class_id = int(det[-1])
            confidence = det[4]

            label = COCO_CLASSES[class_id]

            if label in ("person", "cell phone") and confidence > 0.6:
                bbox = det[:4].numpy().astype(int)

                if label == "person":
                    person_detected = True
                    cv2.rectangle(
                        frame, (bbox[0], bbox[1]), (bbox[2], bbox[3]), (0, 255, 0), 2
                    )
                    cv2.putText(
                        frame,
                        f"{label} {confidence:.2f}",
                        (bbox[0], bbox[1] - 10),
                        cv2.FONT_HERSHEY_SIMPLEX,
                        0.5,
                        (0, 255, 0),
                        2,
                    )
                elif label == "cell phone":
                    phone_detected = True

    if person_detected and phone_detected:
        cv2.imshow("Frame", nth_frame)
    # Display the resulting frame
    else:
        cv2.imshow("Frame", frame)

    # Break the loop on 'q' key press
    if cv2.waitKey(1) & 0xFF == ord("q"):
        break

# Release the capture and close all OpenCV windows
cap.release()
cv2.destroyAllWindows()
