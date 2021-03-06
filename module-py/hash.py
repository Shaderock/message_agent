import hashlib

num_of_zero = 2


def get_block_hash(content: str, prev_hash: str, nonce: str) -> str:
    return hashlib.sha256(f"{content};{prev_hash};{nonce}".encode('utf8')).hexdigest()


def check_hash_rule(hash_sum: str) -> bool:
    if len(hash_sum) < num_of_zero:
        return False
    for i in range(num_of_zero):
        if hash_sum[i] != '0':
            return False
    return True
