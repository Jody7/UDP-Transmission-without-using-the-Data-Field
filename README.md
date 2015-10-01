# UDP-Transmission-without-using-the-Data-Field
A Security through Obscurity approach for Data Transmission of Binary. Lightly "Encrypted" with XOR'ing.

Instead of using the DatagramPacket's Data field, we can send binary data using Port Numbers. However, this is inefficient and can be easily decrypted with knowledge that it is being sent via Port Numbers. Made in an hour or two. Some code is highly inefficient, feel free to fix. Learn or use some of the code if you want.
