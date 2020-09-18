import random
import string
import hash

block = {'content': 'Абракадабра', 'prev-hash': '', 'nonce': ''}


def get_block_hash() -> str:
    return hash.get_block_hash(block['content'], block['prev-hash'], block['nonce'])


def check_nonce() -> bool:
    return hash.check_hash_rule(get_block_hash())


def get_new_nonce() -> str:
    length = random.randint(1, nonce_max_len)
    letters = string.ascii_letters + string.digits + string.punctuation
    return ''.join(random.choice(letters) for i in range(length))



nonce_max_len = 20

while True:
    block['nonce'] = get_new_nonce()
    if check_nonce():
        print(get_block_hash())
        input()
        break
