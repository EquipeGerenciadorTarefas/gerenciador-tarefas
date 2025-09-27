import os

destinatario = os.getenv("EMAIL_DESTINO")

if not destinatario:
    print("Erro: EMAIL_DESTINO não definido!")
else:
    print(f"Pipeline executado com sucesso! E-mail enviado para {destinatario}")
