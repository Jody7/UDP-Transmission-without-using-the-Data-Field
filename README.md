# UDP-Transmission-without-using-the-Data-Field
A Security through Obscurity approach for Data Transmission of Binary. Lightly "Encrypted" with XOR'ing.

Instead of using the DatagramPacket's Data field, we can send binary data using Port Numbers. However, this is inefficent and can be easily decrypted with knowledge that it is being sent via Port Numbers.
