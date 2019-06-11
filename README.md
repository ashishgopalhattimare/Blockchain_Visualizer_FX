
# Blockchain Visualizer FX

The application is built using JavaFX including blockchain concepts. The motive of this application is to help people learning **Blockchain Technology** while visualizing the internal working of the blockchain in simplest way possible.
The model is motivated from <i> [https://blockchaintrainingalliance.com/pages/lab-blocks](https://blockchaintrainingalliance.com/pages/lab-blocks)<i> which provides a web application of Blockchain's internal working.

## Key Points
  
1. <b>Prev : </b> This represents the hash of the previous block in the blockchain, to maintain immutability. If the block is the initial block, or the **Genesis Block**, then the previous hash is set to 0 (default).
2. <b> Nonce : </b> In blockchain, nonce is a 32-bit (4-byte) integer value whic is set so that the hash of the block will contain a run of leading zeros.
The run of leading zeros in the hash generated is also known as **Difficulty** which is directly linked with nonce value. The greater the difficulty, the more difficult to get the nonce value.
Any change to the block data will make the block hash completely different. Nonce of a block is only generated which the block is mined.
3. <b> Hash : </b> It represents the hash of the block as a whole.
	blockHash = GenerateSHA-256( #{nonce}#{prev}#{data} ).

## Working

<p align="center">
  <img src="https://github.com/ashishgopalhattimare/Blockchain_Visualizer_FX/blob/master/screenshots/screenshot1.png" width="500" title="screenshot1.png">
</p>
<p align="center"> <i> Fig.1 - Initial State of the blockchain network where all the node are set to empty with default nonce value and auto generated hash of each block in the blockchain. </i> </p>

<u>Step 1:</u>
Type the following into the data field of Block #1 and click "MINE":
<i>Ashish paid Rajat Rs. 100<i>

<p align="center">
  <img src="https://github.com/ashishgopalhattimare/Blockchain_Visualizer_FX/blob/master/screenshots/screenshot2.png" width="500" title="screenshot2.png">
</p>
<p align="center"> <i> Fig.2 - The Block #1 is appended into the blockchain and mined. </i> </p>

<u>Step 2:</u>
Type the following into the data field of Block #2 and click "MINE":
<i>Rajat paid Akash Rs. 150<i>

<p align="center">
  <img src="https://github.com/ashishgopalhattimare/Blockchain_Visualizer_FX/blob/master/screenshots/screenshot3.png" width="500" title="screenshot3.png">
</p>
<p align="center"> <i> Fig.3 - The Block #2 is appended into the blockchain and mined. A link is formed with the previous block </i> </p>

<u>Step 3:</u>
Type the following into the data field of Block #3 and click "MINE":
<i>Akash paid Zain Rs.100<i>

<p align="center">
  <img src="https://github.com/ashishgopalhattimare/Blockchain_Visualizer_FX/blob/master/screenshots/screenshot4.png" width="500" title="screenshot4.png">
</p>
<p align="center"> <i> Fig.4 - The Block #3 is appended into the blockchain and mined. A link is formed with the previous block. </i> </p>

<u>Step 4:</u>
Now, go back to Block #1, and try to change the Rs. 100 to Rs. 50 and click "MINE".

<p align="center">
  <img src="https://github.com/ashishgopalhattimare/Blockchain_Visualizer_FX/blob/master/screenshots/screenshot5.png" width="500" title="screenshot5.png">
</p>
<p align="center"> <i> Fig.5 - Block #1 has been tampered with new value which resulted in the link break in the blockchain. </i> </p>

It could be visualized that the block has changed its color from green to red as the current nonce is not valid for the current block status. The data has been tampered within on the block and the chain has been broken.

For any hacker to hack the blokchain, they would need to mine all the blocks in this chain to make his update valid for this chain. But in public blockchain, there are nearly 14,000 nodes distrubuted with repitition of data in each node. Thus, this update would no longer match the ledger of the other peer on the network to get a consensus of >= 51%.
