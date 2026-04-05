import cv2
import os

nombre_persona = "marco gonzalez"
path_raw = os.path.join('dataset', nombre_persona)
path_final = os.path.join('dataset-procesado', nombre_persona)

if not os.path.exists(path_final):
    print(f'Carpeta creada: {path_final}')
    os.makedirs(path_final)

# 2. Cargar el clasificador de rostros (Haar Cascade)
face_cascade = cv2.CascadeClassifier(cv2.data.haarcascades + 'haarcascade_frontalface_default.xml')

for image in os.listdir(path_raw):
    img = cv2.imread(os.path.join(path_raw, image))
    if img is None:
        continue

    gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
    faces = face_cascade.detectMultiScale(gray, 1.3, 5)
    count = 0
    for (x, y, w, h) in faces:
        # Recortar el rostro detectado
        rostro_recortado = img[y:y + h, x:x + w]
    
        # Redimensionar al tamaño solicitado (160x160)
        rostro_final = cv2.resize(rostro_recortado, (160, 160))
    
        # Guardar el resultado
        cv2.imwrite(os.path.join(path_final, f'{image}_{count}.jpg'), rostro_final)
        count += 1
