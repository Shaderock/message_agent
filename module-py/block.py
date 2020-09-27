class Block:  # Block for blockchain
    def __init__(self, id_block, content, prev_hash=''):
        self.id_block = id_block
        self.content = content
        self.prev_hash = prev_hash
        self.hash = ''
        self.nonce = ''
