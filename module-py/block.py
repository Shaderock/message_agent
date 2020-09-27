class Block:  # Block for blockchain
    def __init__(self, id_block, content, prev_hash):
        self.id_block = id_block
        self.content = content
        self.prev_hash = prev_hash
        self.hash = ''
        self.nonce = ''

    def __eq__(self, other):
        if not isinstance(other, Block):
            return False
        return self.id_block == other.id_block and self.content == other.content and self.prev_hash == other.prev_hash
